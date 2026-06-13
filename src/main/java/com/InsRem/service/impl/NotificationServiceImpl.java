package com.InsRem.service.impl;

import com.InsRem.entity.Notification;
import com.InsRem.repository.NotificationRepository;
import com.InsRem.service.AgentService;
import com.InsRem.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository repository;

    private final AgentService agentService;

    @Override
    public List<Notification> getNotifications() {

        return repository
                .findByAgentOrderByCreatedAtDesc(
                        agentService.getLoggedInAgent()
                );
    }

    @Override
    public long unreadCount() {

        return repository
                .countByAgentAndIsReadFalse(
                        agentService.getLoggedInAgent()
                );
    }

    @Override
    public void markAsRead(Long id) {

        Notification notification =
                repository.findById(id)
                        .orElseThrow();

        notification.setIsRead(true);

        repository.save(notification);
    }
}