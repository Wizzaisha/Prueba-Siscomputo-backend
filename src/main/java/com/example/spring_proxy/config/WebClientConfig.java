package com.example.spring_proxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    @Value("${external.api.baseurl}")
    private String externalApiUrl;

    @Bean
    public WebClient webClient () {
        return WebClient.builder()
                .baseUrl(externalApiUrl)
                .filter((request, next) -> {
                    System.out.println("Request URL: " + request.method() + " " + request.url());
                    return next.exchange(request)
                            .doOnNext(response ->
                                    System.out.println("Status: " + response.statusCode())
                            );
                })
                .build();
    }
}
