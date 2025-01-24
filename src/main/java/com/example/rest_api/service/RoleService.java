package com.example.rest_api.service;

import java.util.List;

import com.example.rest_api.dto.RoleDTO;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);

    Long updateRole(long id, RoleDTO roleDTO);

    List<RoleDTO> getAllRole();

    RoleDTO findRoleById(long id);

    void deleteRole(long id);
}
