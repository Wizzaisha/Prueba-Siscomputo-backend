package com.example.spring_proxy.service;

import com.example.spring_proxy.dto.ExternalResponseDTO;
import com.example.spring_proxy.exception.ResourceNotFoundException;
import com.example.spring_proxy.exception.ResourceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExternalService {
    private final WebClient webClient;


    @Autowired
    public ExternalService (WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ExternalResponseDTO> getAllData() {
        return webClient.get().uri("/todos")
                .retrieve()
                .bodyToFlux(ExternalResponseDTO.class)
                .onErrorMap(exception -> new ResourceUnavailableException("upstream_unavailable"));
    }

    public Mono<ExternalResponseDTO> getDataById(Long id) {
        return webClient.get().uri("/todos/{id}", id).retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response ->
                        Mono.error(new ResourceNotFoundException("not_found")))
                .bodyToMono(ExternalResponseDTO.class)
                .onErrorMap(exception -> {
                    if (exception instanceof ResourceNotFoundException) {
                        return exception;
                    }
                    return new ResourceUnavailableException("upstream_unavailable");
                });
    }

}
