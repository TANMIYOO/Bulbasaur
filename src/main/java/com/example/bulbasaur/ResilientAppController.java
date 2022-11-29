package com.example.bulbasaur;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * ResilientAppController
 *
 * @author TianMingYu
 * @version 1.0.0
 * @date 2022-11-28 17:45
 */
@RestController
@RequestMapping("/api")
public class ResilientAppController {

    private final ExternalAPICaller externalAPICaller;

    public ResilientAppController(ExternalAPICaller externalAPICaller) {
        this.externalAPICaller = externalAPICaller;
    }

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "CircuitBreakerService")
    public String circuitBreakerApi() {
        return externalAPICaller.callApi();
    }

    @GetMapping("/retry")
    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    public String retryApi() {
        return externalAPICaller.callApi();
    }

    public String fallbackAfterRetry(Exception ex) {
        return "all retries have exhausted";
    }

    @GetMapping("/time-limiter")
    @TimeLimiter(name = "timeLimiterApi")
    public CompletableFuture<String> timeLimiterApi() {
        return CompletableFuture.supplyAsync(externalAPICaller::callApiWithDelay);
    }

    @GetMapping("/bulkheadApi")
    @Bulkhead(name = "bulkheadApi")
    public String bulkheadApi() {
        return externalAPICaller.callApi();
    }

    @GetMapping("/rate-limiter")
    @RateLimiter(name = "rateLimiterApi")
    public String rateLimiterApi() {
        return externalAPICaller.callApi();
    }

}
