package com.InsRem.service;

public interface ForgotPasswordService {

    void sendResetLink(
            String email
    );

    void resetPassword(
            String token,
            String password
    );
}