package com.fred.schoolmanagement.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailsManagement {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailsManagement(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmails(String to, String subject, String message ){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

}
