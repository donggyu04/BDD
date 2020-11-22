package com.training.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@CucumberContextConfiguration
@SpringBootTest(classes = BddApplication.class, webEnvironment = DEFINED_PORT)
public class SpringIntegrationTest {

    protected ResponseResults latestResponse = null;

    @Autowired
    public RestTemplate restTemplate;

    public void executeGet(String url) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        restTemplate.setErrorHandler(errorHandler);

        latestResponse = restTemplate.execute(url, GET, requestCallback, response -> {
            if (errorHandler.hadError) {
                return (errorHandler.getResponseResults());
            } else {
                return (new ResponseResults(response));
            }
        });
    }

    void executePost() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate
                .execute("http://localhost:8082/baeldung", POST, requestCallback, response -> {
                    if (errorHandler.hadError) {
                        return (errorHandler.getResponseResults());
                    } else {
                        return (new ResponseResults(response));
                    }
                });
    }

    private static class ResponseResultErrorHandler implements ResponseErrorHandler {

        private ResponseResults responseResults = null;
        private Boolean hadError = false;

        private ResponseResults getResponseResults() {
            return responseResults;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            responseResults = new ResponseResults(response);
        }
    }
}
