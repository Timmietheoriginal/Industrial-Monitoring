package com.example.Industrial_monitoring.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendEmail(String recipient, String subject, String body){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("sogunletimilehin@gmail.com");
            message.setTo(recipient);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            System.out.println("Email successfully sent to: " + recipient);
        } catch (Exception e){
            System.out.println("Failed to send email alert: " + e.getMessage());
            e.printStackTrace();
        }

    }


}
