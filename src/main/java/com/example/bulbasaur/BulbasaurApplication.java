package com.example.bulbasaur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author MI
 */
@SpringBootApplication
public class BulbasaurApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulbasaurApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri("http://localhost:9090").build();
    }
}
