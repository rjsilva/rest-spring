package br.com.rjs.rest_spring.dto;

import java.util.Date;

public record NewPersonRequestDto(
        Long id,
        String  name,
        String address,
        Date birthDay,
        Character gender
) {}
