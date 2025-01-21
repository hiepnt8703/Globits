package com.example.rest_api.service;

import java.util.List;

import com.example.rest_api.dto.CountryDTO;

public interface CountryService {
    CountryDTO save(CountryDTO country);

    Long updateCountry(long id, CountryDTO countryDTO);

    List<CountryDTO> getAll();

    void delete(long id);

    CountryDTO findById(long id);
}
