package com.example.rest_api.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.rest_api.domain.User;

public class UserDTO {
    private long id;
    private String email;
    private String password;
    private Boolean is_active;
    private PersonDTO personDTO;
    private Set<Long> roleIds = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(long id, String email, String password, Boolean is_active, PersonDTO personDTO) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.is_active = is_active;
        this.personDTO = personDTO;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.is_active = user.getIs_active();
        if (user.getPerson() != null) {
            this.personDTO = new PersonDTO(user.getPerson());
        }
        if (user.getRoles() != null) {
            for (var role : user.getRoles()) {
                this.roleIds.add(role.getId());
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }

}
