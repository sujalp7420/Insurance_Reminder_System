package com.InsRem.controller;

import com.InsRem.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    @GetMapping
    public String notifications(Model model) {

        model.addAttribute(
                "notifications",
                service.getNotifications());

        return "notifications";
    }

    @GetMapping("/read/{id}")
    public String read(
            @PathVariable Long id) {

        service.markAsRead(id);

        return "redirect:/notifications";
    }
}