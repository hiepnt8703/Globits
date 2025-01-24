package com.example.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_api.dto.RoleDTO;
import com.example.rest_api.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    @PutMapping("/{id}")
    public Long updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        return roleService.updateRole(id, roleDTO);
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRole();
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.findRoleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
