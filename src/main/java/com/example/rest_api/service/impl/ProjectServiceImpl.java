package com.example.rest_api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.rest_api.domain.Company;
import com.example.rest_api.domain.Person;
import com.example.rest_api.domain.Project;
import com.example.rest_api.dto.ProjectDTO;
import com.example.rest_api.repository.CompanyRepository;
import com.example.rest_api.repository.PersonRepository;
import com.example.rest_api.repository.ProjectRepository;
import com.example.rest_api.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setCode(projectDTO.getCode());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        Optional<Company> companyOptional = companyRepository.findById(projectDTO.getCompanyId());
        if (companyOptional.isPresent()) {
            project.setCompany(companyOptional.get());
        } else {
            throw new RuntimeException("Company not found with ID: " + projectDTO.getCompanyId());
        }
        Set<Person> persons = new HashSet<>();
        for (Long personId : projectDTO.getPersonIds()) {
            Person person = personRepository.findById(personId)
                    .orElseThrow(() -> new RuntimeException("Person not found with ID: " + personId));
            persons.add(person);
        }
        project.setPersons(persons);
        projectRepository.save(project);
        return new ProjectDTO(project);
    }

    @Override
    public Long updateProject(long id, ProjectDTO projectDTO) {
        Optional<Project> projectOptional = projectRepository.findById(projectDTO.getId());
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setCode(projectDTO.getCode());
            project.setName(projectDTO.getName());
            project.setDescription(projectDTO.getDescription());
            Optional<Company> companyOptional = companyRepository.findById(projectDTO.getCompanyId());
            if (companyOptional.isPresent()) {
                project.setCompany(companyOptional.get());
            }
            Set<Person> persons = new HashSet<>();
            for (Long personId : projectDTO.getPersonIds()) {
                Person person = personRepository.findById(personId)
                        .orElseThrow(() -> new RuntimeException("Person not found with ID: " + personId));
                persons.add(person);
            }
            projectRepository.save(project);
        } else {
            throw new RuntimeException("Company not found with ID: " + projectDTO.getCompanyId());
        }
        return projectDTO.getId();
    }

    @Override
    public List<ProjectDTO> getAllProject() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<>();

        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO(project);
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }

    @Override
    public ProjectDTO getProjectById(long id) {
        Optional<Project> projecOptional = projectRepository.findById(id);
        if (projecOptional.isPresent()) {
            return new ProjectDTO(projecOptional.get());
        } else {
            throw new RuntimeException("Project with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteProject(long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            projectRepository.delete(projectOptional.get());
        }
    }

    @Override
    public Page<ProjectDTO> getPageProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(ProjectDTO::new);
    }

}
