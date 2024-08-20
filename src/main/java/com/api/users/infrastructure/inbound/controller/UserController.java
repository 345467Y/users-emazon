package com.api.users.infrastructure.inbound.controller;

import com.api.users.domain.model.UserDTO;
import com.api.users.mapper.UserMapper;
import com.api.users.domain.repository.UserRepository;
import com.api.users.persistencia.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping
    private List<UserDTO> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userEntity -> userMapper.userEntityToUserDTO(userEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    private UserDTO getUserById(@PathVariable Long id) {
        Optional<UserEntity> userOptional = userRepository
                .findById(id);
        if(userOptional.isPresent()){
            return userOptional.map(userEntity -> userMapper.userEntityToUserDTO(userEntity)).get();
        }
        return null;
    }


    @PostMapping
    private UserDTO saveUser(@RequestBody UserDTO user) {
        UserEntity userEntity = this.userRepository.save(
                userMapper.userDTOToUserEntity(user)
        );
        return userMapper.userEntityToUserDTO(userEntity);
    }

}
