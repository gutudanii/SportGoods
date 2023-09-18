package com.ecommerce.SportGoods.mail;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(Mail mail, String fName, String link) {
        String bdy = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Welcome to Our E-Commerce Sportswear Store</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f2f2f2;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "            border: 1px solid #ccc;\n" +
                "        }\n" +
                "        .header {\n" +
                "            padding: 20px 0;\n" +
                "            text-align: center;\n" +
                "            background-color: #007bff;\n" +
                "        }\n" +
                "        .header h1 {\n" +
                "            color: white;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "        .content a {\n" +
                "            color: #007bff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            padding: 20px;\n" +
                "            text-align: center;\n" +
                "            background-color: #f2f2f2;\n" +
                "        }\n" +
                "        .footer p {\n" +
                "            color: #555;\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1>Welcome to E-Commerce Sportswear Store!</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Dear ," +fName+
                "</p>\n" +
                "            <p>Welcome to  E-Commerce Sportswear Store ! We are excited to have you join our community of sportswear enthusiasts. Get ready to experience the latest trends, high-quality products, and a seamless shopping experience.</p>\n" +
                "<div align=\"center\" style=\"margin: 50px; align-items: center; text-align: center;\">\n" +
                "  <p style=\"font-size: 20px;\">Please Activate using the below button</p>\n" +
                "  <a style=\"padding: 10px; background-color: green; text-decoration: none; border-radius: 10px; color: #fff;\" " +
                "href=" +
                "\"" +
                link +
                "\"" +
                ">Acitvate</a>\n" +
                "</div>"
                +
                "            <img width=\"100%\" src=\"https://media.istockphoto.com/id/1097874048/photo/womens-fitness-outfit-isolated-on-white-background.jpg?s=612x612&w=0&k=20&c=CSEo4tqV0e8vjsD-ItssNrqyxUBcLEypco_92r7ilkY=\" alt=\"\">\n" +
                "            <p>To start shopping, simply <a href=\"http://localhost:8080/users/login\">Log In</a> to your account.</p>\n" +
                "            <p>If you have any questions or need assistance, our dedicated support team is here to help. Feel free to reach out to us at <a href=\"mailto:support@example.com\">support@example.com</a>.</p>\n" +
                "            <p>Thank you for choosing E-Commerce Sport Wear. We look forward to helping you achieve your fitness goals in style!</p>\n" +
                "            <p>Best Regards,<br>Sports Wear<br>noreplysportwear@gmail.com</p>\n" +
                "            <p>P.S. Don't forget to follow us on https://... for exciting updates, contests, and more!</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>This email was sent from E-Commerce Sport Wear. If you no longer wish to receive emails from us, you can <a href=\"[Unsubscribe Link]\">unsubscribe here</a>.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
                mimeMailMessage.setFrom("noreplysportwear@gmail.com");
                mimeMailMessage.setText(bdy, true);
                mimeMailMessage.setSubject("Welcome to E-Commerce Sport Wear");
                mimeMailMessage.addTo(mail.getEmail());
        };
        this.javaMailSender.send(mimeMessagePreparator);
    }

    @Override
    public void sendNotification(Mail mail, String subject, String fName) {
        String msg ="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Welcome to Our E-Commerce Sportswear Store</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f2f2f2;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            border-radius: 20px;\n" +
                "            width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #fff;\n" +
                "        }\n" +
                "        .header {\n" +
                "            padding: 20px 0;\n" +
                "            text-align: center;\n" +
                "            background-color: #113966;\n" +
                "            border-radius: 20px 20px 10px 10px;\n" +
                "        }\n" +
                "        .header h1 {\n" +
                "            color: white;\n" +
                "            font-size: large;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "        .content a {\n" +
                "            color: #007bff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            padding: 20px;\n" +
                "            text-align: center;\n" +
                "            background-color: #f2f2f2;\n" +
                "        }\n" +
                "        .footer p {\n" +
                "            color: #555;\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1>Your Response is Successfully Submitted!</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Dear," + fName+
                " </p>\n" +
                "            <p>Your Response (Feedback) is successfully submitted, our team wil contact you soon.</p>\n" +
                "            <p>This is to confirm that we have received your Response or Feedback under subject :" + subject +
                " </p>\n"+
                "            <img width=\"100%\" src=\"https://media.istockphoto.com/id/1097874048/photo/womens-fitness-outfit-isolated-on-white-background.jpg?s=612x612&w=0&k=20&c=CSEo4tqV0e8vjsD-ItssNrqyxUBcLEypco_92r7ilkY=\" alt=\"\">\n" +
                "            <p>To start shopping, simply <a href=\"http://localhost:8080/users/login\">Log In</a> to your account.</p>\n" +
                "            <p>If you have any questions or need assistance, our dedicated support team is here to help. Feel free to reach out to us at <a href=\"mailto:support@example.com\">support@example.com</a>.</p>\n" +
                "            <p>Thank you for choosing E-Commerce Sport Wear. We look forward to helping you achieve your fitness goals in style!</p>\n" +
                "            <p>Best Regards,<br>Sports Wear<br>noreplysportwear@gmail.com</p>\n" +
                "            <p>P.S. Don't forget to follow us on https://... for exciting updates, contests, and more!</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>This email was sent from E-Commerce Sport Wear. If you no longer wish to receive emails from us, you can <a href=\"[Unsubscribe Link]\">unsubscribe here</a>.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMailMessage.setFrom("noreplysportwear@gmail.com");
            mimeMailMessage.setText(msg, true);
            mimeMailMessage.setSubject("Your Response Successfully Submitted : " + fName);
            mimeMailMessage.addTo(mail.getEmail());
        };
        this.javaMailSender.send(mimeMessagePreparator);
    }
}
