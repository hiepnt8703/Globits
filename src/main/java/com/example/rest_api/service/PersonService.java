package com.example.rest_api.service;

import java.util.List;

import com.example.rest_api.dto.PersonDTO;

public interface PersonService {
    PersonDTO savePerson(PersonDTO personDTO);

    Long updatePerson(long id, PersonDTO personDTO);

    PersonDTO findPersonByid(long id);

    List<PersonDTO> getAllPerson();

    void deletePerson(long id);
}
