package com.example.demo.Api;

import com.example.demo.Model.Person;
import com.example.demo.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService){
        this.personService=personService;
    }
    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }
    @GetMapping
    public List<Person> getPersons(){
        return personService.getPersons();
    }
    @GetMapping("{id}")
    public Person getPersonById(@PathVariable(value = "id") UUID id){
        return  personService.getPersonById(id).orElse(null);
    }
    @DeleteMapping("{id}")
    public int deletePerson(@PathVariable(value = "id") UUID id){
        return  personService.deletePersonById(id);
    }
    @PutMapping("{id}")
    public  void updatePerson(@PathVariable(value = "id") UUID id,@RequestBody Person person){
        personService.updatePerson(id,person);
    }

}
