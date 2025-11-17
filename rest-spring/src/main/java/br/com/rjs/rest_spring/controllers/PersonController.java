package br.com.rjs.rest_spring.controllers;

import br.com.rjs.rest_spring.model.Person;
import br.com.rjs.rest_spring.service.PersonService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<Void> addPerson(@RequestBody Person person){
        if(person == null) throw new UnsupportedOperationException("Person cannot be null");
        personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getPeople(){
        List<Person> people = personService.getAllPeople();
        return ResponseEntity.status(HttpStatus.OK).body(people);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removePerson(@PathVariable String id){
        personService.removedPersonById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> removePerson(@PathVariable String id, @RequestBody Person person){
        personService.updatePerson(id, person);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
