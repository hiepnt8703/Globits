package com.example.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest_api.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    
}
