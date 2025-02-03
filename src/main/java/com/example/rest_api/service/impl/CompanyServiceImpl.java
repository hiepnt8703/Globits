package com.example.rest_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.rest_api.domain.Company;
import com.example.rest_api.dto.CompanyDTO;
import com.example.rest_api.repository.CompanyRepository;
import com.example.rest_api.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setCode(companyDTO.getCode());
        company.setAddress(companyDTO.getAddress());

        companyRepository.save(company);
        return new CompanyDTO(company);
    }

    @Override
    public CompanyDTO updateCompany(long id, CompanyDTO companyDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(companyDTO.getName());
            company.setCode(companyDTO.getCode());
            company.setAddress(companyDTO.getAddress());
            companyRepository.save(company);
            return new CompanyDTO(company);
        } else {
            throw new RuntimeException("Company with ID " + id + " not found");
        }
    }

    @Override
    public CompanyDTO getCompanyById(long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.map(CompanyDTO::new).orElse(null);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (Company company : companies) {
            companyDTOs.add(new CompanyDTO(company));
        }
        return companyDTOs;
    }

    @Override
    public void deleteCompany(long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            companyRepository.delete(optionalCompany.get());
        } else {
            throw new RuntimeException("Company with ID " + id + " not found");
        }
    }

    @Override
    public Page<CompanyDTO> getPageCompany(Pageable pageable) {
        return companyRepository.findAll(pageable).map(CompanyDTO::new);
    }

}
