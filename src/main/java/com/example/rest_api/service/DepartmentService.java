package com.example.rest_api.service;

import java.util.List;

import com.example.rest_api.dto.DepartmentDTO;

public interface DepartmentService {
    List<DepartmentDTO> getDepartmentsByCompanyId(Long companyId);
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    Long updateDepartment(Long id, DepartmentDTO departmentDTO);
    void deleteDepartment(Long id);

}
