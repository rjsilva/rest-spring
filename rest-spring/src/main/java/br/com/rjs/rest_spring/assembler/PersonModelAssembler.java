package br.com.rjs.rest_spring.assembler;

import br.com.rjs.rest_spring.controllers.PersonController;
import br.com.rjs.rest_spring.dto.PersonResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonModelAssembler
        implements RepresentationModelAssembler<PersonResponseDto, EntityModel<PersonResponseDto>> {

    @Override
    public EntityModel<PersonResponseDto> toModel(PersonResponseDto dto) {

        return EntityModel.of(
                dto,
                linkTo(methodOn(PersonController.class).findById(dto.id())).withRel("findOne").withType("GET"),
                linkTo(methodOn(PersonController.class).getAllPeople()).withRel("findAll").withType("GET"),
                linkTo(methodOn(PersonController.class).updatePerson(dto.id(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(PersonController.class).deletePerson(dto.id())).withRel("delete").withType("DELETE"),
                linkTo(methodOn(PersonController.class).addPerson(null)).withRel("save").withType("POST")
        );
    }
}
