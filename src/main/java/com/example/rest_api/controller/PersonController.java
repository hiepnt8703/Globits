package com.example.rest_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_api.dto.PersonDTO;
import com.example.rest_api.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  private static final String UPLOAD_DIR = "uploads/avatars";

  @PostMapping
  public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
    try {
      PersonDTO savedPerson = personService.savePerson(personDTO);
      return ResponseEntity.ok(savedPerson);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Long> updatePerson(@PathVariable long id,
      @RequestBody PersonDTO personDTO) {
    try {
      Long updatedPersonId = personService.updatePerson(id, personDTO);
      return ResponseEntity.ok(updatedPersonId);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(null); // Trả về lỗi với mã 400 nếu có ngoại lệ xảy ra
    }
  }

  @GetMapping
  public ResponseEntity<List<PersonDTO>> getAll() {
    List<PersonDTO> persons = personService.getAllPerson();
    return ResponseEntity.ok(persons);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonDTO> getPersonById(@PathVariable long id) {
    try {
      // Gọi phương thức findById từ Service
      PersonDTO person = personService.findPersonByid(id);

      // Trả về phản hồi HTTP 200 với đối tượng PersonDTO
      return ResponseEntity.ok(person);
    } catch (RuntimeException e) {
      // Trả về phản hồi HTTP 404 nếu không tìm thấy
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePerson(@PathVariable long id) {
    try {
      personService.deletePerson(id);
      return ResponseEntity.ok("Person with ID " + id + " has been deleted.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(null);
    }
  }

  @GetMapping("/page")
  public ResponseEntity<Page<PersonDTO>> getPageProject(Pageable pageable) {
    Page<PersonDTO> personPage = personService.getPagePerson(pageable);
    return ResponseEntity.ok(personPage);
  }

  @PostMapping("/{id}/upload-avatar")
  public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
      @RequestParam("file") MultipartFile file) {
    try {
      String message = personService.uploadAvatar(id, file);
      return ResponseEntity.ok(message);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
    }
  }
}
