package com.InsRem.repository;

import com.InsRem.entity.Agent;
import com.InsRem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByAgentOrderByCreatedAtDesc(
            Agent agent
    );

    long countByAgentAndIsReadFalse(
            Agent agent
    );
}