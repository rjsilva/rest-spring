package br.com.rjs.rest_spring.controllers;

import br.com.rjs.rest_spring.dto.PersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonResponseDto;
import br.com.rjs.rest_spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(value = "/add")
    public ResponseEntity<PersonResponseDto> addPerson(@RequestBody PersonRequestDto dto){
        personService.addPerson(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<PersonResponseDto>> getAllPeople(){
        List<PersonResponseDto> people = personService.getAllPeople();
        return ResponseEntity.status(HttpStatus.OK).body(people);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){
        personService.removedPersonById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonResponseDto> updatePerson(@PathVariable Long id, @RequestBody PersonRequestDto dto){
        personService.updatePerson(id, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
