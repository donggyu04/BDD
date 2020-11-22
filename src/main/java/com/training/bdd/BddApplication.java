package com.training.bdd;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class BddApplication {

    public static void main(String[] args) {
        run(BddApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
