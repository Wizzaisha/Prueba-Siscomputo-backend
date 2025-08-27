package com.example.spring_proxy.controller;

import com.example.spring_proxy.dto.ExternalResponseDTO;
import com.example.spring_proxy.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/external")
public class ExternalController {

     private final ExternalService externalService;

     @Autowired
        public ExternalController (ExternalService externalService) {
            this.externalService = externalService;
     }

    @GetMapping
    public ResponseEntity<Flux<ExternalResponseDTO>> getAllExternalData() {
        Flux<ExternalResponseDTO> responseDTO = externalService.getAllData();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ExternalResponseDTO>> getExternalDataById (@PathVariable Long id) {
        Mono<ExternalResponseDTO> responseDTOMono = externalService.getDataById(id);
        return ResponseEntity.ok(responseDTOMono);
    }

}
