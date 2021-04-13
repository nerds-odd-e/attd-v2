package com.odde.atddv2.config;

import com.odde.atddv2.repo.UserRepo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Base64;

import static com.odde.atddv2.config.TokenFilter.Token.makeToken;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;

class TokenFilterTest {
    private UserRepo userRepo = mock(UserRepo.class);
    private TokenFilter tokenFilter = new TokenFilter(userRepo);
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private FilterChain filterChain = mock(FilterChain.class);

    @Nested
    class Login {

        public static final int EPSILON = 20;

        @Test
        public void should_pass_for_options_method_api_call() {
            restCall("/api/orders", null, OPTIONS);

            shouldPass();
        }

        @Test
        void should_not_pass_if_not_set_token_header() {
            restCall("/api/orders", null, GET);

            shouldNotPass();
        }

        @Test
        void should_pass_for_non_api_call() {
            restCall("/users/login", null, GET);

            shouldPass();
        }

        @Test
        void should_pass_when_give_valid_token() {
            restCall("/api/orders", existUserToken("tom"), GET);

            shouldPass();
        }

        @Test
        void should_not_pass_when_user_not_exist() {
            restCall("/api/orders", makeToken("not exist user"), GET);

            shouldNotPass();
        }

        @Test
        void should_not_pass_when_token_is_not_a_valid_base64() {
            restCall("/api/orders", "invalid base64 prefix" + existUserToken("tom"), GET);

            shouldNotPass();
        }

        @Test
        void should_not_pass_when_token_is_not_a_valid_json() {
            givenUserInDb("tom");
            restCall("/api/orders", Base64.getEncoder().encodeToString("invalid token".getBytes()), GET);

            shouldNotPass();
        }

        @Test
        void should_not_pass_when_token_is_not_a_token_json() {
            givenUserInDb("tom");
            restCall("/api/orders", Base64.getEncoder().encodeToString("{}".getBytes()), GET);

            shouldNotPass();
        }

        @Test
        void should_not_pass_when_token_expired() {
            givenUserInDb("tom");
            String expiredToken = new TokenFilter.Token().setUser("tom")
                    .setNow(Instant.now().plusSeconds(-TokenFilter.Token.EXPIRATION - EPSILON).getEpochSecond()).toString();

            restCall("/api/orders", expiredToken, GET);

            shouldNotPass();
        }

        private String existUserToken(String username) {
            givenUserInDb(username);
            return makeToken(username);
        }

        private void givenUserInDb(String username) {
            when(userRepo.existsByUserName(username)).thenReturn(true);
        }

        @SneakyThrows
        private void restCall(String url, String token, HttpMethod method) {
            when(request.getRequestURI()).thenReturn(url);
            when(request.getHeader("Token")).thenReturn(token);
            when(request.getMethod()).thenReturn(method.name());

            tokenFilter.doFilter(request, response, filterChain);
        }

        @SneakyThrows
        private void shouldNotPass() {
            verify(response).sendError(401);
            verify(filterChain, never()).doFilter(any(), any());
        }

        @SneakyThrows
        private void shouldPass() {
            verify(response, never()).sendError(anyInt());
            verify(filterChain).doFilter(any(), any());
        }
    }
}