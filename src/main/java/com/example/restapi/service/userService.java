package com.example.restapi.service;

import com.example.restapi.entity.user;
import com.example.restapi.jwt.UserRequest;
import com.example.restapi.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userService {
    public List<UserDto> getAllusers();
    public UserDto getUserById(int id);
    public List<UserDto> searchUser(String name);
    public UserRequest insertUser(UserRequest userDto);
    public UserDto updateUser(int id, UserDto userDto);
    public void deleteUser(int id);
}
