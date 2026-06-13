package com.InsRem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String address;

    private String profileImage;

    private String password;

    private String mobile;

    @Column(name = "reset_token")
    private String resetToken;

    private String companyName;

    private String status;

    @Column(name = "token_expiry")
    private LocalDateTime tokenExpiry;
}