package com.example.rest_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rest_api.domain.Company;
import com.example.rest_api.domain.Person;
import com.example.rest_api.dto.PersonDTO;
import com.example.rest_api.repository.CompanyRepository;
import com.example.rest_api.repository.PersonRepository;
import com.example.rest_api.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {

        Optional<Company> companyOptional = companyRepository.findById(personDTO.getCompanyDTO().getId());
        if (companyOptional.isPresent()) {
            Person person = new Person();
            person.setFullName(personDTO.getFullName());
            person.setGender(personDTO.getGender());
            person.setBirthdate(personDTO.getBirthdate());
            person.setPhoneNumber(personDTO.getPhoneNumber());
            person.setAddress(personDTO.getAddress());
            person.setCompany(companyOptional.get());
            person = personRepository.save(person);
            return new PersonDTO(person);
        } else {
            throw new RuntimeException("Person with ID " + personDTO.getId() + " not found");
        }
    }

    @Override
    public Long updatePerson(long id, PersonDTO personDTO) {
        Optional<Person> optionalPerson = this.personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            Optional<Company> companyOptional = companyRepository.findById(personDTO.getCompanyDTO().getId());
            if (companyOptional.isPresent()) {
                Person person = optionalPerson.get();
                person.setFullName(personDTO.getFullName());
                person.setGender(personDTO.getGender());
                person.setBirthdate(personDTO.getBirthdate());
                person.setPhoneNumber(personDTO.getPhoneNumber());
                person.setAddress(personDTO.getAddress());
                person.setCompany(companyOptional.get());
                person = personRepository.save(person);
                return person.getId();
            } else {
                throw new RuntimeException("Company with ID " + id + " not found");
            }
        } else {
            throw new RuntimeException("Person with ID " + id + " not found");
        }
    }

    @Override
    public List<PersonDTO> getAllPerson() {
        List<Person> persons = this.personRepository.findAll();
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (Person person : persons) {
            PersonDTO personDTO = new PersonDTO(person);
            personDTOs.add(personDTO);
        }
        return personDTOs;
    }

    @Override
    public PersonDTO findPersonByid(long id) {
        Optional<Person> personOptional = this.personRepository.findById(id);
        if (personOptional.isPresent()) {
            // Person person = personOptional.get();

            // PersonDTO personDTO = new PersonDTO();
            // personDTO.setFullName(person.getFullName());
            // personDTO.setGender(person.getGender());
            // personDTO.setBirthdate(person.getBirthdate());
            // personDTO.setPhoneNumber(person.getPhoneNumber());
            // personDTO.setAddress(person.getPhoneNumber());
            return new PersonDTO(personOptional.get());
        } else {
            throw new RuntimeException("Person with ID " + id + " not found.");
        }
    }

    @Override
    public void deletePerson(long id) {
        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isPresent()) {
            personRepository.delete(personOptional.get());
        } else {
            throw new RuntimeException("Person with ID " + id + " not found");
        }
    }

}
