package com.ecommerce.SportGoods.controller;

import com.ecommerce.SportGoods.mail.Mail;
import com.ecommerce.SportGoods.mail.MailService;
import com.ecommerce.SportGoods.model.Notification;
import com.ecommerce.SportGoods.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class NotificationController {


    @Autowired
    private final NotificationService notificationService;

    @Autowired
    private final MailService mailService;
    @PostMapping("/save/contact")
    public String saveContact(@ModelAttribute Notification notification, Model model){
       try {
           Mail mail = new Mail();
           mail.setEmail(notification.getEmail());
           mailService.sendNotification(mail, notification.getSubject() ,notification.getFullName());
           notificationService.save(notification);
           model.addAttribute("notification", new Notification());
           model.addAttribute("message", true);
       }
       catch (Exception e){

       }
        return "contact.html";
    }

    @GetMapping("/contact")
    public String getContactForm(Model model){
        model.addAttribute("notification", new Notification());
        return "contact.html";
    }

}

