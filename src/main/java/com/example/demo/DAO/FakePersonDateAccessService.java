package com.example.demo.DAO;

import com.example.demo.Model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakePersonDateAccessService implements PersonDao {
    private  static List<Person> DB=new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }
    @Override
    public  List<Person> getPersons(){
        return  DB;
    }
    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(p->p.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe=selectPersonById(id);
        if(personMaybe.isPresent()){
            DB.remove(personMaybe.get());
            return  1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person updatePerson) {
        return selectPersonById(id).map(person->{
            int indexOfPersonDelete=DB.indexOf(person);
            if(indexOfPersonDelete>=0){
                DB.set(indexOfPersonDelete,new Person(person.getId(),updatePerson.getName()));
                return  1;
            }
            return  0;
        }).orElse(0);
    }
}
