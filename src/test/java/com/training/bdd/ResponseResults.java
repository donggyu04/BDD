package com.training.bdd;

import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.IOUtils.copy;

public class ResponseResults {

    private final ClientHttpResponse clientHttpResponse;

    private final String body;

    ResponseResults(ClientHttpResponse clientHttpResponse) throws IOException {
        this.clientHttpResponse = clientHttpResponse;
        InputStream bodyInputStream = clientHttpResponse.getBody();
        StringWriter stringWriter = new StringWriter();
        copy(bodyInputStream, stringWriter, UTF_8);
        body = stringWriter.toString();
    }

    public ClientHttpResponse getClientHttpResponse() {
        return clientHttpResponse;
    }

    public String getBody() {
        return body;
    }
}
