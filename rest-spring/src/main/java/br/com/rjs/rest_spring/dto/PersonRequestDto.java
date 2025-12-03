package br.com.rjs.rest_spring.dto;

import jakarta.persistence.Column;

public record PersonRequestDto(
        Long id,
        String  name,
        String address,
        Character gender
) {}
