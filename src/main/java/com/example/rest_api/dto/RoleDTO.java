package com.example.rest_api.dto;

import com.example.rest_api.domain.Role;

public class RoleDTO {
    private long id;
    private String role;
    private String description;

    public RoleDTO() {
    }

    public RoleDTO(long id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
        this.description = role.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
