package com.example.rest_api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.rest_api.dto.ProjectDTO;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);

    Long updateProject(long id, ProjectDTO projectDTO);

    List<ProjectDTO> getAllProject();

    ProjectDTO getProjectById(long id);

    void deleteProject(long id);

    Page<ProjectDTO> getPageProjects(Pageable pageable);
}
