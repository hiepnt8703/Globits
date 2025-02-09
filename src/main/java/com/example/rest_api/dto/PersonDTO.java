package com.example.rest_api.dto;

import java.time.LocalDate;

import com.example.rest_api.domain.Person;
import com.example.rest_api.domain.User;

public class PersonDTO {
    private long id;

    private String fullName;
    private String gender;
    private LocalDate birthdate;
    private String phoneNumber;
    private String address;
    private CompanyDTO companyDTO;
    private UserDTO user;

    public PersonDTO() {
    }

    public PersonDTO(long id, String fullName, String gender, LocalDate birthdate, String phoneNumber, String address,
            CompanyDTO companyDTO , UserDTO user) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.companyDTO = companyDTO;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.gender = person.getGender();
        this.birthdate = person.getBirthdate();
        this.phoneNumber = person.getPhoneNumber();
        this.address = person.getAddress();
        if (person.getCompany() != null) {
            this.companyDTO = new CompanyDTO(person.getCompany());
        }
        if (person.getUser() != null){
            this.user = new UserDTO(person.getUser());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
