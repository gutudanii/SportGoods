package com.ecommerce.SportGoods.repository;

import com.ecommerce.SportGoods.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getAllByisRead(boolean isRead);
}
