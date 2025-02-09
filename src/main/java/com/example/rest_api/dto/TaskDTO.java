package com.example.rest_api.dto;

import com.example.rest_api.domain.Person;
import com.example.rest_api.domain.Project;
import com.example.rest_api.domain.Task;
import com.example.rest_api.util.TaskPriority;
import com.example.rest_api.util.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

public class TaskDTO {
    private long id;
    private ProjectDTO project;
    private PersonDTO person;
    private LocalDate startTime;
    private LocalDate endTime;
    private TaskPriority priority;
    private String name;
    private String description;
    private TaskStatus status;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.project = new ProjectDTO(task.getProject());
        this.person = new PersonDTO(task.getPerson());
        this.name = task.getName();
        this.description = task.getDescription();
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
    }

    public TaskDTO(long id, ProjectDTO project, PersonDTO person, LocalDate startTime, LocalDate endTime, TaskPriority priority, String name, String description, TaskStatus status) {
        this.id = id;
        this.project = project;
        this.person = person;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
