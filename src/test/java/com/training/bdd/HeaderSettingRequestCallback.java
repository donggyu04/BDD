package com.training.bdd;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;
import java.util.Map;

public class HeaderSettingRequestCallback implements RequestCallback {

    private final Map<String, String> requestHeaders;

    private String body;

    public HeaderSettingRequestCallback(Map<String, String> headers) {
        requestHeaders = headers;
    }

    @Override
    public void doWithRequest(ClientHttpRequest request) throws IOException {
        HttpHeaders clientHeaders = request.getHeaders();

        for (final Map.Entry<String, String> entry : requestHeaders.entrySet()) {
            clientHeaders.add(entry.getKey(), entry.getValue());
        }

        if (null != body) {
            request.getBody().write(body.getBytes());
        }
    }

    public void setBody(String body) {
        this.body = body;
    }
}
