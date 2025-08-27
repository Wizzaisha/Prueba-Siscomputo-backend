package com.example.spring_proxy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExternalResponseDTO {
    private Long id;
    private Long userId;
    private String title;
    private boolean completed;
}
