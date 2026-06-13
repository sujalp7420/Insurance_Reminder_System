package com.InsRem.service.impl;

import com.InsRem.entity.*;
import com.InsRem.repository.*;
import com.InsRem.service.EmailService;
import com.InsRem.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl
        implements ReminderService {

    private final PolicyRepository policyRepository;

    private final EmailService emailService;

    private final ReminderHistoryRepository
            reminderHistoryRepository;

    private final NotificationRepository
            notificationRepository;

    @Override
    public void sendReminders() {

        LocalDate today = LocalDate.now();

        process(today.plusDays(2),
                "EXPIRING_IN_2_DAYS");

        process(today.plusDays(1),
                "EXPIRING_TOMORROW");

        process(today,
                "EXPIRED_TODAY");
    }

    private void process(
            LocalDate date,
            String reminderType) {

        List<Policy> policies =
                policyRepository
                        .findByExpiryDate(date);

        for (Policy policy : policies) {

            boolean alreadySent =
                    reminderHistoryRepository
                            .findByPolicyAndReminderType(
                                    policy,
                                    reminderType
                            )
                            .isPresent();

            if (alreadySent) {
                continue;
            }

            String body =
                    "Customer : "
                            + policy.getCustomerName()

                            + "\nPolicy Number : "
                            + policy.getPolicyNumber()

                            + "\nCompany : "
                            + policy.getCompanyName()

                            + "\nExpiry Date : "
                            + policy.getExpiryDate();

            emailService.sendEmail(
                    policy.getAgent().getEmail(),
                    reminderType,
                    body
            );

            ReminderHistory history =
                    ReminderHistory.builder()
                            .policy(policy)
                            .agent(policy.getAgent())
                            .reminderType(reminderType)
                            .emailStatus("SUCCESS")
                            .sentAt(
                                    LocalDateTime.now()
                            )
                            .build();

            reminderHistoryRepository
                    .save(history);

            Notification notification =
                    Notification.builder()
                            .agent(policy.getAgent())
                            .title(reminderType)
                            .message(body)
                            .isRead(false)
                            .createdAt(
                                    LocalDateTime.now()
                            )
                            .build();

            notificationRepository
                    .save(notification);
        }
    }
}