package com.odde.atddv2;

import io.cucumber.java.Before;
import lombok.SneakyThrows;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.UnaryOperator;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class MockServer {
    @Autowired
    private MockServerClient mockServerClient;

    @Before(order = 0)
    @SneakyThrows
    public void resetMockServer() {
        mockServerClient.reset();
    }

    public void getJson(String path, UnaryOperator<HttpRequest> params, String response) {
        mockServerClient.when(params.apply(request().withMethod("GET").withPath(path)), Times.unlimited())
                .respond(response().withStatusCode(200)
                        .withHeader(CONTENT_TYPE, "application/json")
                        .withBody(response));
    }
}