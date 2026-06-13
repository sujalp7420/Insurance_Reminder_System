package com.InsRem.controller;

import com.InsRem.repository.ReminderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderHistoryRepository
            repository;

    @GetMapping("/reminders")
    public String reminders(Model model) {

        model.addAttribute(
                "reminders",
                repository.findAll()
        );

        return "reminders";
    }
}