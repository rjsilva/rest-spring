package br.com.rjs.rest_spring.controllers;

import br.com.rjs.rest_spring.assembler.PersonModelAssembler;
import br.com.rjs.rest_spring.dto.PersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonResponseDto;
import br.com.rjs.rest_spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonModelAssembler assembler;

    @PostMapping(value = "/add")
    public ResponseEntity<EntityModel<PersonResponseDto>> addPerson(@RequestBody PersonRequestDto dto){
        PersonResponseDto saved = personService.addPerson(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(saved));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){
        personService.removedPersonById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EntityModel<PersonResponseDto>> updatePerson(@PathVariable Long id, @RequestBody PersonRequestDto dto){
        PersonResponseDto personResponseDto = personService.updatePerson(id, dto);
        return ResponseEntity.ok(assembler.toModel(personResponseDto));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<EntityModel<PersonResponseDto>>> getAllPeople(){
        List<PersonResponseDto> people = personService.getAllPeople();
        List<EntityModel<PersonResponseDto>> models = people.stream()
                .map(assembler::toModel)
                .toList();

        return ResponseEntity.ok(models);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<PersonResponseDto>> findById(@PathVariable Long id) {
        PersonResponseDto dto = personService.findById(id);
        return ResponseEntity.ok(assembler.toModel(dto));
    }
}
