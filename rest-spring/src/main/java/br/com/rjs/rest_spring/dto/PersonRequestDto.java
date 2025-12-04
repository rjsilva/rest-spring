package br.com.rjs.rest_spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public record PersonRequestDto(
        Long id,
        String  name,
        String address,
        Date birthDay,
        Character gender
) {}
