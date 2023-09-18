package com.ecommerce.SportGoods.service;

import com.ecommerce.SportGoods.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    void save(Notification notification);

    List<Notification> getAllNotifications();

    List<Notification> getAllByIsRead(boolean isRead);
}
