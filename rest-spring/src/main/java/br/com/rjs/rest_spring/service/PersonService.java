package br.com.rjs.rest_spring.service;

import br.com.rjs.rest_spring.dao.PersonRepository;
import br.com.rjs.rest_spring.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public void addPerson(Person person){
        logger.info("Adicionando uma pessoa");
        PersonRepository.addPerson(person);
    }

    public List<Person> getAllPeople(){
        logger.info("Recuperando uma lista de pessoas");
        return PersonRepository.getAllPeople();
    }

    public void removedPersonById(String id){
        logger.info("Removendo uma pessoa da lista");
        int convertedId = Integer.parseInt(id);
        PersonRepository.remove(convertedId);
    }

    public void updatePerson(String id, Person person){
        int convertedId = Integer.parseInt(id);
        logger.info("Atualizando uma pessoa da lista");
        PersonRepository.atualizar(convertedId, person);
    }
}
