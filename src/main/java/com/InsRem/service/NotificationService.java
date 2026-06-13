package com.InsRem.service;

import com.InsRem.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getNotifications();

    long unreadCount();

    void markAsRead(Long id);
}