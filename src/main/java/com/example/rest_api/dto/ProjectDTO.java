package com.example.rest_api.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.rest_api.domain.Person;
import com.example.rest_api.domain.Project;

public class ProjectDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Long companyId;
    private List<Long> personIds;

    public ProjectDTO() {
    }

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.code = project.getCode();
        this.name = project.getName();
        this.description = project.getDescription();

        // Map Company ID
        if (project.getCompany() != null) {
            this.companyId = project.getCompany().getId();
        }
        // Map list of Person IDs
        if (project.getPersons() != null && !project.getPersons().isEmpty()) {
            this.personIds = project.getPersons().stream()
                    .map(Person::getId)
                    .collect(Collectors.toList());
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Long> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<Long> personIds) {
        this.personIds = personIds;
    }

}
