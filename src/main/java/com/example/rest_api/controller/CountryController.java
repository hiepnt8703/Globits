package com.example.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_api.dto.CountryDTO;
import com.example.rest_api.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    // API để lưu đối tượng Country mới
    @PostMapping
    public ResponseEntity<CountryDTO> saveCountry(@RequestBody CountryDTO country) {
        CountryDTO savedCountry = countryService.save(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCountry(
            @PathVariable long id,
            @RequestBody CountryDTO countryDTO) {
        try {
            Long updatedId = countryService.updateCountry(id, countryDTO);
            if (updatedId != null) {
                return ResponseEntity.ok(updatedId);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        List<CountryDTO> countries = countryService.getAll();
        return ResponseEntity.ok(countries);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable long id) {
        try {
            countryService.delete(id);
            return ResponseEntity.ok("Country with ID " + id + " has been deleted.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable long id) {
        try {
            CountryDTO countryDTO = countryService.findById(id);
            return ResponseEntity.ok(countryDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
