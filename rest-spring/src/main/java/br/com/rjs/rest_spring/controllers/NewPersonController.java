package br.com.rjs.rest_spring.controllers;
import br.com.rjs.rest_spring.dto.NewPersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonResponseDto;
import br.com.rjs.rest_spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person/v2")
public class NewPersonController {


    @Autowired
    private PersonService personService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponseDto> addPerson(@RequestBody NewPersonRequestDto dto){
        personService.addNewPerson(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
