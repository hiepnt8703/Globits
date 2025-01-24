package com.example.rest_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest_api.domain.Role;
import com.example.rest_api.dto.RoleDTO;
import com.example.rest_api.repository.RoleRepository;
import com.example.rest_api.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();

        role.setRole(roleDTO.getRole());
        role.setDescription(roleDTO.getDescription());
        roleRepository.save(role);

        return new RoleDTO(role);
    }

    @Override
    public Long updateRole(long id, RoleDTO roleDTO) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setDescription(roleDTO.getDescription());
            role.setRole(roleDTO.getRole());
            roleRepository.save(role);
            return role.getId();
        } else {
            throw new RuntimeException("User with ID " + roleDTO.getId() + " not found");
        }
    }

    @Override
    public List<RoleDTO> getAllRole() {
        List<Role> roles = this.roleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();

        for (Role role : roles) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setDescription(role.getDescription());
            roleDTO.setRole(role.getRole());
            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }

    @Override
    public RoleDTO findRoleById(long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()) {
            return new RoleDTO(roleOptional.get());
        } else {
            throw new RuntimeException("Person with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteRole(long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()) {
            roleRepository.delete(roleOptional.get());
        }
    }

}
