package com.example.bulbasaur;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * ExternalAPICaller
 *
 * @author TianMingYu
 * @version 1.0.0
 * @date 2022-11-28 17:44
 */
@Component
public class ExternalAPICaller {

    private final RestTemplate restTemplate;

    public ExternalAPICaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callApi() {
        return restTemplate.getForObject("/api/external", String.class);
    }

    public String callApiWithDelay() {
        String result = restTemplate.getForObject("/api/external", String.class);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
        return result;
    }
}
