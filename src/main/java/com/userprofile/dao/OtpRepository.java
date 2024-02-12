package com.userprofile.dao;

import com.userprofile.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp,Long> {
    public Optional<Otp> findByOtpCodeAndUserId(String otpCode,Long userId);
}