package com.userprofile.entities;

import jakarta.persistence.*;

@Entity
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String otpCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Otp(String otpCode, User user) {
        this.otpCode = otpCode;
        this.user = user;
    }
}
