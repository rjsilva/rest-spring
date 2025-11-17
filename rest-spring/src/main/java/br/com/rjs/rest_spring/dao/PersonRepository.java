package br.com.rjs.rest_spring.dao;
import br.com.rjs.rest_spring.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonRepository {

    private final static List<Person> people = new ArrayList<>();
    private final static AtomicInteger counter = new AtomicInteger(0);

    public static void addPerson(Person person){
        int newValue = counter.getAndIncrement();
        Person p = new Person(newValue, person.getName(), person.getAddress(), person.getGender());
        people.add(p);
    }

    public static List<Person> getAllPeople(){
        return people;
    }

    public static void remove(int id){
        Person person = people.get(id);
        if (person != null) {
            people.remove(person);
        }
    }

    public static void atualizar(int id, Person person){
        Person personUpdated = people.get(person.getId());
        personUpdated.setId(id);
        personUpdated.setAddress(person.getAddress());
        personUpdated.setName(person.getName());
        personUpdated.setGender(person.getGender());
        people.add(personUpdated);
        people.remove(id);
    }
}
