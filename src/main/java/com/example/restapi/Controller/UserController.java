package com.example.restapi.Controller;

import com.example.restapi.jwt.UserRequest;
import com.example.restapi.model.dto.UserDto;
import com.example.restapi.security.SecurityConfig;
import com.example.restapi.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private userService userService;
    @Autowired
    private SecurityConfig security;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> GetUser(@PathVariable int id) {
        UserDto user=userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping({"/test"})
    public String test() {
        return "test";
    }
    @GetMapping("/users")
    public ResponseEntity<?> getListUser() {
        List<UserDto> listUser=userService.getAllusers();
        return ResponseEntity.ok(listUser);
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name="keyword",required = false,defaultValue ="") String name) {
        List<UserDto> listUser=userService.searchUser(name);
        return ResponseEntity.ok(listUser);
    }
    @PostMapping("/create")
    public ResponseEntity<?> CreateUser(@RequestBody UserRequest user) {
        user.setPassword(security.passwordEncoder().encode(user.getPassword()));
        UserRequest savedUser=userService.insertUser(user);
        System.out.println(user.toString());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateUser(@RequestBody UserDto user, @PathVariable int id) {
        UserDto userUpdate=userService.updateUser(id, user);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
