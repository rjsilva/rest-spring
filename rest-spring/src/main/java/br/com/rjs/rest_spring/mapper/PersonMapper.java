package br.com.rjs.rest_spring.mapper;

import br.com.rjs.rest_spring.dto.PersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonResponseDto;
import br.com.rjs.rest_spring.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonRequestDto dto);

    PersonResponseDto toResponse(Person person);

    void updateEntity(PersonRequestDto dto, @MappingTarget Person entity);
}
