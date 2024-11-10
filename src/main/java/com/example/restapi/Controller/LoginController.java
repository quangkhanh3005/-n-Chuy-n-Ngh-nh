package com.example.restapi.Controller;

import com.example.restapi.jwt.LoginRequest;
import com.example.restapi.jwt.LoginResponse;
import com.example.restapi.jwt.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Tên người dùng: " + loginRequest.getUserName());
        System.out.println("Mật khẩu: " + loginRequest.getPass());
        String token = authenticationService.login(loginRequest.getUserName(), loginRequest.getPass());
        System.out.println(token);
        if (token.startsWith("Authentication failed")) {
            return new LoginResponse("null", "Authentication failed");
        }
        return new LoginResponse(token, "Login successful");
    }


}
