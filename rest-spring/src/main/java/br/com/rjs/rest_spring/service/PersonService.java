package br.com.rjs.rest_spring.service;

import br.com.rjs.rest_spring.exception.ResourceNotFoundException;
import br.com.rjs.rest_spring.repository.PersonRepository;
import br.com.rjs.rest_spring.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public void addPerson(Person person){
        logger.warn("Adicionando uma person");
        personRepository.save(person);
    }

    public List<Person> getAllPeople(){
        logger.warn("Recuperando uma lista de pessoas");
        return personRepository.findAll();
    }

    public void removedPersonById(Long id){
        logger.warn("Removendo uma pessoa da lista");
        Person entity = personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(entity);
    }

    public void updatePerson(Long id, Person person){
        logger.warn("Atualizando uma pessoa da lista");
        Person entity = personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Person not found"));
        entity.setId(id);
        entity.setName(person.getName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        personRepository.save(entity);
    }
}
