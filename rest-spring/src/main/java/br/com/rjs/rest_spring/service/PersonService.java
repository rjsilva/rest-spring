package br.com.rjs.rest_spring.service;

import br.com.rjs.rest_spring.exception.ResourceNotFoundException;
import br.com.rjs.rest_spring.repository.PersonRepository;
import br.com.rjs.rest_spring.repository.PersonRepositoryLegado;
import br.com.rjs.rest_spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public void addPerson(Person person){
        logger.info("Adicionando uma pessoa");
        personRepository.save(person);
    }

    public List<Person> getAllPeople(){
        logger.info("Recuperando uma lista de pessoas");
        return personRepository.findAll();
    }

    public void removedPersonById(Long id){
        logger.info("Removendo uma pessoa da lista");
        Person entity = personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(entity);
    }

    public void updatePerson(Long id, Person person){
        logger.info("Atualizando uma pessoa da lista");
        Person entity = personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Person not found"));
        entity.setId(id);
        entity.setName(person.getName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        personRepository.save(entity);
    }
}
