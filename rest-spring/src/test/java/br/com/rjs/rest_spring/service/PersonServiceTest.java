package br.com.rjs.rest_spring.service;

import br.com.rjs.rest_spring.dto.NewPersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonRequestDto;
import br.com.rjs.rest_spring.dto.PersonResponseDto;
import br.com.rjs.rest_spring.mapper.PersonMapper;
import br.com.rjs.rest_spring.model.Person;
import br.com.rjs.rest_spring.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonMapper mapper;

    @BeforeEach
    void setUp() {}

    @Test
    void addPerson() {
        PersonRequestDto req = new PersonRequestDto(1L, "Ronaldo", "rua teste", null, 'M');
        Person entity = new Person();
        Person saved = new Person();
        saved.setId(10L);

        PersonResponseDto resp = new PersonResponseDto(10L, "Ronaldo", 'M', "address", null);

        Mockito.when(mapper.toEntity(req)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(saved);
        Mockito.when(mapper.toResponse(saved)).thenReturn(resp);

        // Act
        PersonResponseDto result = service.addPerson(req);

        // Assert
        assertEquals(resp, result);
        Mockito.verify(mapper).toEntity(req);
        Mockito.verify(repository).save(entity);
        Mockito.verify(mapper).toResponse(saved);
    }

    @Test
    void addNewPerson() {
        NewPersonRequestDto req = new NewPersonRequestDto(1L, "Ronaldo", "rua teste", new Date(), 'M');
        Person entity = new Person();
        Person saved = new Person();
        saved.setId(10L);

        PersonResponseDto resp = new PersonResponseDto(10L, "Ronaldo", 'M', "address", null);

        Mockito.when(mapper.toNewEntity(req)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(saved);
        Mockito.when(mapper.toResponse(saved)).thenReturn(resp);

        // Act
        PersonResponseDto result = service.addNewPerson(req);

        // Assert
        assertEquals(resp, result);
        Mockito.verify(mapper).toNewEntity(req);
        Mockito.verify(repository).save(entity);
        Mockito.verify(mapper).toResponse(saved);
    }

    @Test
    void removedPersonById() {}

    @Test
    void updatePerson() {
        PersonRequestDto dto = new PersonRequestDto(1L, "Maria", "rua teste", null, 'M');
        Person person = new Person();
        person.setId(1L);

        PersonResponseDto resp = new PersonResponseDto(1L, "joao", 'M',"rua teste", null);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));
        Mockito.when(repository.save(person)).thenReturn(person);
        Mockito.when(mapper.toResponse(person)).thenReturn(resp);

        // Act
        PersonResponseDto result = service.updatePerson(1L, dto);

        // Assert
        assertEquals(resp, result);
        Mockito.verify(repository).findById(1L);
        Mockito.verify(mapper).updateEntity(dto, person);
        Mockito.verify(repository).save(person);
        Mockito.verify(mapper).toResponse(person);
    }

    @Test
    void findById() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Ronaldo");
        PersonResponseDto dto = new PersonResponseDto(1L, "Ronaldo", 'M', "address", null);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(mapper.toResponse(person)).thenReturn(dto);
        PersonResponseDto result = service.findById(1L);
        assertEquals(dto, result);
        verify(repository).findById(1L);
        verify(mapper).toResponse(person);
    }

    @Test
    void getAllPeople() {
        Person p1 = new Person();
        Person p2 = new Person();

        PersonResponseDto r1 = new PersonResponseDto(1L, "A", 'F', "addr", null);
        PersonResponseDto r2 = new PersonResponseDto(2L, "B", 'M', "addr", null);

        Mockito.when(repository.findAll()).thenReturn(List.of(p1, p2));
        Mockito.when(mapper.toResponse(p1)).thenReturn(r1);
        Mockito.when(mapper.toResponse(p2)).thenReturn(r2);

        List<PersonResponseDto> result = service.getAllPeople();

        assertEquals(2, result.size());
        assertEquals(r1, result.get(0));
        assertEquals(r2, result.get(1));
    }
}