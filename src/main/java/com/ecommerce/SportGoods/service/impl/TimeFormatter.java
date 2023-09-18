package com.ecommerce.SportGoods.service.impl;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeFormatter {
    public String formatTimeAgo(LocalDateTime timestamp) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(timestamp, now);

        if (duration.toMinutes() < 1) {
            return "just now";
        } else if (duration.toHours() < 1) {
            return duration.toMinutes() + " minutes ago";
        } else if (duration.toDays() < 1) {
            return duration.toHours() + " hours ago";
        } else if (duration.toDays() < 7) {
            return duration.toDays() + " days ago";
        } else if (duration.toDays() >= 7 && duration.toDays() < 30) {
            return (duration.toDays() / 7) + " weeks ago";
        } else {
            return timestamp.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }
}
