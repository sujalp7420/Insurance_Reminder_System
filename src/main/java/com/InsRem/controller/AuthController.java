package com.InsRem.controller;

import com.InsRem.dto.RegisterRequest;
import com.InsRem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/")
    public String home() {

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute(
                "registerRequest",
                new RegisterRequest());

        return "register";
    }

    @PostMapping("/register")
    public String register(

            @Valid

            @ModelAttribute RegisterRequest request,

            BindingResult result,

            Model model) {

        if(result.hasErrors()) {

            return "register";
        }

        authService.register(request);

        return "redirect:/login";
    }
}