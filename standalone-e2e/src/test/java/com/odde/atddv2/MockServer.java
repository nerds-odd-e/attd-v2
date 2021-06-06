package com.odde.atddv2;

import io.cucumber.java.Before;
import lombok.SneakyThrows;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.function.UnaryOperator;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class MockServer {
    private MockServerClient mockServerClient;

    @Value("${mock-server.endpoint}")
    private String endpoint;


    @Before(order = 0)
    @SneakyThrows
    public void resetMockServer() {
        mockServerClient = createMockServerClient(new URL(endpoint));
        mockServerClient.reset();
    }

    public void getJson(String path, UnaryOperator<HttpRequest> params, String response) {
        getJson(path, params, Times.unlimited(), response);
    }

    public void getJson(String path, UnaryOperator<HttpRequest> params, Times times, String response) {
        mockServerClient.when(params.apply(request().withMethod("GET").withPath(path)), times)
                .respond(response().withStatusCode(200)
                        .withHeader(CONTENT_TYPE, "application/json")
                        .withBody(response));
    }

    private MockServerClient createMockServerClient(URL url) {
        return mockServerClient = new MockServerClient(url.getHost(), url.getPort()) {
            @Override
            public void close() {
            }
        };
    }
}