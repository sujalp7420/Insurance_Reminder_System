package com.InsRem.controller;

import com.InsRem.service.AgentService;
import com.InsRem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final AuthService authService;
    private final AgentService agentService;

    @GetMapping("/profile")
    public String profile(Model model) {

        model.addAttribute(
                "agent",
                agentService.getLoggedInAgent());

        return "profile";
    }

    @GetMapping("/change-password")
    public String passwordPage() {

        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(

            @RequestParam String oldPassword,

            @RequestParam String newPassword) {

        authService.changePassword(
                oldPassword,
                newPassword);

        return "redirect:/profile";
    }

    @PostMapping("/profile/upload")
    public String uploadProfile(

            @RequestParam MultipartFile image)

            throws Exception {

        agentService.uploadProfileImage(
                image);

        return "redirect:/profile";
    }
}