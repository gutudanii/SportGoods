package com.ecommerce.SportGoods.service.impl;

import com.ecommerce.SportGoods.model.Notification;
import com.ecommerce.SportGoods.repository.NotificationRepository;
import com.ecommerce.SportGoods.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private TimeFormatter timeFormatter;

    @Autowired
    private final NotificationRepository notificationRepository;

    @Override
    public void save(Notification notification) {
        LocalDateTime timestamp = LocalDateTime.now();
        notification.setTimestamp(timestamp);
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        for (Notification notification : notifications) {
            LocalDateTime timestamp = notification.getTimestamp();
            String formattedTime = timeFormatter.formatTimeAgo(timestamp);
            notification.setFormattedTimestamp(formattedTime); // Assuming you have a setter for formattedTimestamp
        }
        return notifications;

    }
        @Override
    public List<Notification> getAllByIsRead(boolean isRead) {
        return notificationRepository.getAllByisRead(isRead);
    }
}
