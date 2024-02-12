package com.userprofile.controller;

import com.userprofile.dao.UserRepository;
import com.userprofile.entities.User;
import com.userprofile.entities.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/test")
    public ResponseEntity<String> getMessage(){

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("method called ");

    }
}
