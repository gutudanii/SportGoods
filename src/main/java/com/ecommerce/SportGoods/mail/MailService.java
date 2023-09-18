package com.ecommerce.SportGoods.mail;

import org.springframework.stereotype.Service;

@Service
public interface MailService {

    void sendMail(Mail mail, String fName, String link);
    void sendNotification(Mail mail, String subject , String fName);
}
