package com.example.rest_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest_api.domain.Country;
import com.example.rest_api.dto.CountryDTO;
import com.example.rest_api.repository.CountryRepository;
import com.example.rest_api.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryDTO save(CountryDTO country) {
        Country newCountry = new Country();
        newCountry.setName(country.getName());
        newCountry.setCode(country.getCode());
        newCountry.setDescription(country.getDescription());

        this.countryRepository.save(newCountry);
        return new CountryDTO(newCountry);
    }

    @Override
    public Long updateCountry(long id, CountryDTO countryDTO) {
        Optional<Country> optionalCountry = this.countryRepository.findById(id);

        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();

            country.setName(countryDTO.getName());
            country.setCode(countryDTO.getCode());
            country.setDescription(countryDTO.getDescription());

            this.countryRepository.save(country);
            return country.getId();
        }
        return null;
    }

    @Override
    public List<CountryDTO> getAll() {
        List<Country> countries = countryRepository.findAll();

        // Chuyển đổi danh sách Country sang danh sách CountryDTO
        List<CountryDTO> countryDTOs = new ArrayList<>();
        for (Country country : countries) {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setId(country.getId());
            countryDTO.setName(country.getName());
            countryDTO.setCode(country.getCode());
            countryDTO.setDescription(country.getDescription());
            countryDTOs.add(countryDTO);
        }

        return countryDTOs;
    }

    @Override
    public void delete(long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        if (optionalCountry.isPresent()) {
            countryRepository.delete(optionalCountry.get());
        } else {
            // Nếu không tồn tại, ném ngoại lệ hoặc xử lý lỗi
            throw new RuntimeException("Country with ID " + id + " not found.");
        }
    }

    @Override
    public CountryDTO findById(long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();

            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setId(country.getId());
            countryDTO.setName(country.getName());
            countryDTO.setCode(country.getCode());
            countryDTO.setDescription(country.getDescription());

            return countryDTO;
        } else {
            throw new RuntimeException("Country with ID " + id + " not found.");
        }
    }

}