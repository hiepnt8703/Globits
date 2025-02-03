package com.example.rest_api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.rest_api.dto.CompanyDTO;

public interface CompanyService {
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(long id, CompanyDTO companyDTO);
    CompanyDTO getCompanyById(long id);
    List<CompanyDTO> getAllCompanies();
    void deleteCompany(long id);

    Page<CompanyDTO> getPageCompany(Pageable pageable);
}
