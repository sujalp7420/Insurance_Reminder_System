package com.InsRem.scheduler;

import com.InsRem.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PolicyReminderScheduler {

    private final ReminderService
            reminderService;

    @Scheduled(cron = "0 0 22 * * *")
    public void runReminder() {

        reminderService.sendReminders();
    }
}