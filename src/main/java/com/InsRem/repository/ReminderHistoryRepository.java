package com.InsRem.repository;

import com.InsRem.entity.Policy;
import com.InsRem.entity.ReminderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReminderHistoryRepository
        extends JpaRepository<ReminderHistory, Long> {

    Optional<ReminderHistory>
    findByPolicyAndReminderType(
            Policy policy,
            String reminderType
    );
}