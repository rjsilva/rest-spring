package br.com.rjs.rest_spring.service;

import br.com.rjs.rest_spring.dto.NewPersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonResponseDto;
import br.com.rjs.rest_spring.exception.ResourceNotFoundException;
import br.com.rjs.rest_spring.mapper.PersonMapper;
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
    @Autowired
    private PersonMapper personMapper;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonResponseDto addPerson(PersonRequestDto dto){
        logger.warn("Adicionando uma person");
        Person person = personMapper.toEntity(dto);
        return personMapper.toResponse(personRepository.save(person));
    }

    public PersonResponseDto addNewPerson(NewPersonRequestDto newPersonRequestDto){
        logger.warn("Adicionando uma nova person");
        Person person = personMapper.toNewEntity(newPersonRequestDto);
        return personMapper.toResponse(personRepository.save(person));
    }

    public List<PersonResponseDto> getAllPeople(){
        logger.warn("Recuperando uma lista de pessoas");
        return personRepository.findAll()
                .stream()
                .map(personMapper::toResponse)
                .toList();
    }

    public void removedPersonById(Long id){
        logger.warn("Removendo uma pessoa da lista");
        if(!personRepository.existsById(id)){
            throw new ResourceNotFoundException("Person not found");
        }
        personRepository.deleteById(id);
    }

    public PersonResponseDto updatePerson(Long id, PersonRequestDto dto){
        logger.warn("Atualizando uma pessoa da lista");
        Person entity = personRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Person not found"));
        personMapper.updateEntity(dto, entity);
        Person person = personRepository.save(entity);
        return personMapper.toResponse(person);
    }

    public PersonResponseDto findById(Long id){
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person no found"));
        return personMapper.toResponse(entity);
    }
}
