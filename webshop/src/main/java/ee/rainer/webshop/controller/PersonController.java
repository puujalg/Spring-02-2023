package ee.rainer.webshop.controller;

import ee.rainer.webshop.model.database.Person;
import ee.rainer.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("getpersons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("getperson/{personalCode}")
    public Person getPerson(@PathVariable String personalCode) {
        return personRepository.findById(personalCode).get();
    }

    @DeleteMapping("deleteperson/{personalCode}")
    public List<Person> deletePerson(@PathVariable String personalCode) {
        personRepository.deleteById(personalCode);
        return personRepository.findAll();
    }

    /**
    @PostMapping("addperson")
    public List<Person> addPerson(@RequestBody Person person) {
        if (person.getPersonalCode() == null || personRepository.findById(person.getPersonalCode())) {
            personRepository.save(person);
        }
        return personRepository.findAll();
    }

    @PutMapping("editperson")
    public List<Person> editPerson(@RequestBody Person person) {
        if (personRepository.findById(person.getPersonalCode()).get().isPresent()) {
            personRepository.save(person);
        }
        return personRepository.findAll();
    }
    */
    
}
