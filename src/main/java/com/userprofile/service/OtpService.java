package com.userprofile.service;

import com.userprofile.dao.OtpRepository;
import com.userprofile.entities.Otp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    public Otp saveOtp(Otp otp) {
        return otpRepository.save(otp);
    }
    public Otp getOtpByCodeAndUserId(String otpCode, Long userId) {
        return otpRepository.findByOtpCodeAndUserId(otpCode, userId)
                .orElseThrow(() -> new RuntimeException("OTP not found with code: " + otpCode + " and user ID: " + userId));
    }

}
