package com.example.restapi.service;

import com.example.restapi.entity.user;
import com.example.restapi.exception.NotFoundException;
import com.example.restapi.jwt.UserRequest;
import com.example.restapi.mapper.userMapper;
import com.example.restapi.model.dto.UserDto;
import com.example.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class userServiceIpl implements userService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllusers() {
        List<user> listUSer = userRepository.findAll();
        return listUSer.stream().map((User) ->userMapper.toUserDto(User)).collect(Collectors.toList());
    }


    @Override
    public UserDto getUserById(int id) {
         user us = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
        return userMapper.toUserDto(us);
    }
    @Override
    public List<UserDto> searchUser(String name) {
        List<user> listUser=userRepository.findAll();
        return listUser.stream().filter(User->User.getUsername().toLowerCase().contains(name.toLowerCase()))
                .map((User) ->userMapper.toUserDto(User)).collect(Collectors.toList());
    }

    @Override
    public UserRequest insertUser(UserRequest userrequest) {
        user us = userMapper.requestToUser(userrequest);
        user savedUser = userRepository.save(us);
        return userMapper.userToUserRequest(savedUser);
    }

    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        user userUpdate=userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
        userUpdate.setUsername(userDto.getUsername());
        userUpdate.setEmail(userDto.getEmail());
        userRepository.save(userUpdate);
        return userMapper.toUserDto(userUpdate);
    }

    @Override
    public void deleteUser(int id) {
        user userDel=userRepository.findById(id).orElseThrow(()->new NotFoundException("User with ID " + id + " not found"));
        userRepository.delete(userDel);
    }


}
