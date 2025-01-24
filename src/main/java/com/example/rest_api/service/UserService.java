package com.example.rest_api.service;

import java.util.List;

import com.example.rest_api.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Long updateUser(long id, UserDTO userDTO);
    UserDTO getUserById(long id);
    List<UserDTO> getAllUsers();
    void deleteUser(long id);
}
