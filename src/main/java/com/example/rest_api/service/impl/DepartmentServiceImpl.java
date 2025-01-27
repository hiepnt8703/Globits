package com.example.rest_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest_api.domain.Company;
import com.example.rest_api.domain.Department;
import com.example.rest_api.dto.DepartmentDTO;
import com.example.rest_api.repository.CompanyRepository;
import com.example.rest_api.repository.DepartmentRepository;
import com.example.rest_api.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<DepartmentDTO> getDepartmentsByCompanyId(Long companyId) {
        List<Department> departments = this.departmentRepository.findByCompanyId(companyId);
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();

        for (Department department : departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setCode(department.getCode());
            departmentDTO.setName(department.getName());
            departmentDTO.setParentId(department.getParent().getId());
            departmentDTO.setCompanyId(department.getCompany().getId());

            departmentDTOs.add(departmentDTO);
        }

        return departmentDTOs;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Optional<Company> companyOptional = companyRepository.findById(departmentDTO.getCompanyId());

        if (companyOptional.isPresent()) {
            Department department = new Department();
            department.setCode(departmentDTO.getCode());
            department.setName(departmentDTO.getName());
            department.setCompany(companyOptional.get());
            Optional<Department> parentOptional = departmentRepository.findById(departmentDTO.getParentId());
            if (parentOptional.isPresent()) {
                department.setParent(parentOptional.get());
            }
            department = departmentRepository.save(department);
            return new DepartmentDTO(department);
        } else {
            throw new RuntimeException("Company with ID " + departmentDTO.getId() + " not found");
        }

    }

    @Override
    public Long updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        if (!departmentOptional.isPresent()) {
            throw new RuntimeException("Department with ID " + id + " not found");
        }

        Department department = departmentOptional.get();
        department.setCode(departmentDTO.getCode());
        department.setName(departmentDTO.getName());

        if (departmentDTO.getParentId() != null) {
            Optional<Department> parentOptional = departmentRepository.findById(departmentDTO.getParentId());
            if (parentOptional.isPresent()) {
                department.setParent(parentOptional.get());
            }
        } else {
            department.setParent(null);
        }

        department = departmentRepository.save(department);
        return departmentDTO.getId();
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

}
