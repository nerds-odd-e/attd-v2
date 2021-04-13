package com.odde.atddv2.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odde.atddv2.repo.UserRepo;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Base64;

import static com.odde.atddv2.config.TokenFilter.Token.parseToken;
import static org.springframework.http.HttpMethod.OPTIONS;

@Component
public class TokenFilter implements Filter {
    private final UserRepo userRepo;

    public TokenFilter(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!isApiCall(req) || isValidToken(req))
            chain.doFilter(request, response);
        else
            ((HttpServletResponse) response).sendError(401);
    }

    private boolean isApiCall(HttpServletRequest req) {
        return req.getRequestURI().startsWith("/api/") && !req.getMethod().equals(OPTIONS.name());
    }

    private boolean isValidToken(HttpServletRequest req) {
        Token token = parseToken(req.getHeader("Token"));
        return token != null && isValidUser(token) && !token.isExpired();
    }

    private boolean isValidUser(Token token) {
        return userRepo.existsByUserName(token.getUser());
    }

    @Data
    @Accessors(chain = true)
    public static class Token {
        public static final int EXPIRATION = 300;
        private String user;
        private long now = Instant.now().getEpochSecond();

        public static String makeToken(String userName) {
            return new Token().setUser(userName).toString();
        }

        @SneakyThrows
        public static Token parseToken(String tokenString) {
            try {
                return new ObjectMapper().readValue(Base64.getDecoder().decode(tokenString), Token.class);
            } catch (Exception ignore) {
                return null;
            }
        }

        @JsonIgnore
        public boolean isExpired() {
            return Instant.now().getEpochSecond() - getNow() > EXPIRATION;
        }

        @Override
        @SneakyThrows
        public String toString() {
            return Base64.getEncoder().encodeToString(new ObjectMapper().writeValueAsBytes(this));
        }
    }
}
