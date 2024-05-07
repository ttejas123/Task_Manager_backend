package com.tejas.googleauth.service.Mail;

public interface MailService {
    void sendMail(String to, String subject, String body);
}
