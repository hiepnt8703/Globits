package com.example.rest_api.dto;

import com.example.rest_api.domain.Department;

public class DepartmentDTO {
    private Long id;
    private String code;
    private String name;
    private Long parentId;
    private Long companyId;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.code = department.getCode();
        this.name = department.getName();
        this.parentId = department.getParent() != null ? department.getParent().getId() : null;
        this.companyId = department.getCompany().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}
