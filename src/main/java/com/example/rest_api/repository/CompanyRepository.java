package com.example.rest_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest_api.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{
    Page<Company> findAll(Pageable pageable);
}
