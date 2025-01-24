package com.example.rest_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest_api.domain.Person;
import com.example.rest_api.domain.User;
import com.example.rest_api.dto.PersonDTO;
import com.example.rest_api.dto.UserDTO;
import com.example.rest_api.repository.PersonRepository;
import com.example.rest_api.repository.UserRepository;
import com.example.rest_api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Optional<Person> personOptional = personRepository.findById(userDTO.getPersonDTO().getId());

        if (personOptional.isPresent()) {
            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setIs_active(userDTO.getIs_active());
            user.setPerson(personOptional.get());
            userRepository.save(user);
            return new UserDTO(user);
        } else {
            throw new RuntimeException("Person with ID " + userDTO.getPersonDTO().getId() + " not found");

        }

    }

    @Override
    public Long updateUser(long id, UserDTO userDTO) {
        Optional<User> useOptional = userRepository.findById(id);

        if (useOptional.isPresent()) {
            User user = useOptional.get();
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setIs_active(userDTO.getIs_active());
            Optional<Person> personOptional = personRepository.findById(userDTO.getPersonDTO().getId());
            if (personOptional.isPresent()) {
                user.setPerson(personOptional.get());
            }
            userRepository.save(user);
            return user.getId();
        } else {
            throw new RuntimeException("User with ID " + userDTO.getId() + " not found");
        }
    }

    @Override
    public UserDTO getUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new UserDTO(userOptional.get());
        } else {
            throw new RuntimeException("Person with ID " + id + " not found.");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId()); // Đặt ID
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword()); // Cân nhắc không đưa mật khẩu
            userDTO.setIs_active(user.getIs_active());

            // Xử lý thông tin Person
            if (user.getPerson() != null) {
                Person person = user.getPerson();
                PersonDTO personDTO = new PersonDTO(person);
                userDTO.setPersonDTO(personDTO); // Gán PersonDTO vào UserDTO
            }

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
    }

}
