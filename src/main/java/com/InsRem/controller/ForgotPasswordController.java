package com.InsRem.controller;

import com.InsRem.service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService service;

    @GetMapping("/forgot-password")
    public String forgotPage() {

        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestParam String email) {

        service.sendResetLink(email);

        return "redirect:/login";
    }

    @GetMapping("/reset-password")
    public String resetPage() {

        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(

            @RequestParam String token,

            @RequestParam String password) {

        service.resetPassword(
                token,
                password
        );

        return "redirect:/login";
    }
}