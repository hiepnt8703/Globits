package com.example.rest_api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.rest_api.dto.PersonDTO;
import org.springframework.web.multipart.MultipartFile;

public interface PersonService {

  PersonDTO savePerson(PersonDTO personDTO);

  Long updatePerson(long id, PersonDTO personDTO);

  PersonDTO findPersonByid(long id);

  List<PersonDTO> getAllPerson();

  void deletePerson(long id);

  Page<PersonDTO> getPagePerson(Pageable pageable);

  String uploadAvatar(Long id, MultipartFile file);
}
