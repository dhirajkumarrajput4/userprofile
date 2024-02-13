package com.userprofile.controller;

import com.userprofile.dao.UserRepository;
import com.userprofile.entities.Otp;
import com.userprofile.entities.User;
import com.userprofile.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/home/user")
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @GetMapping("/test")
    public ResponseEntity<String> getMessage() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("method called ");

    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).get();
        // Logic to send OTP to the user's registered email or phone number
        // we can write the logic for send opt using email or SMS
        String otpCode = generateOtp(); // generateOtp
        Otp otp = new Otp(otpCode, user);
        otpService.saveOtp(otp);
        return ResponseEntity.ok("OTP sent successfully!");
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(@RequestParam("otp") String otpCode, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).get();
        Otp otp = otpService.getOtpByCodeAndUserId(otpCode, user.getId());
        if (otp != null) {
            return ResponseEntity.ok("OTP validated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).get();
        return ResponseEntity.ok(user);
    }

    // for generating random otp
    //6 digit opt
    private String generateOtp() {
        int otp = (int) ((Math.random() * (999999 - 100000)) + 100000);
        return String.valueOf(otp);
    }
}
