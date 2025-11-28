package br.com.rjs.rest_spring.repository;

import br.com.rjs.rest_spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
