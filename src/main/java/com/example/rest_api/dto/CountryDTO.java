package com.example.rest_api.dto;

import com.example.rest_api.domain.Country;

public class CountryDTO {
    private long id;
    private String name;
    private String code;
    private String description;

    public CountryDTO() {
    }

    public CountryDTO(long id, String name, String code, String description) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public CountryDTO(Country country) {
        this.id = country.getId();
        this.name = country.getName();
        this.code = country.getCode();
        this.description = country.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
