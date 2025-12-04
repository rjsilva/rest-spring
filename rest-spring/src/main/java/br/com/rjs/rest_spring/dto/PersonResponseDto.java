package br.com.rjs.rest_spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public record PersonResponseDto(
        Long id,
        String  name,
        Character gender,
        String address,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Date birthDay
) {
}
