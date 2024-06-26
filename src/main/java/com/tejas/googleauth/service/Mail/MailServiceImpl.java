package com.tejas.googleauth.service.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tejas.googleauth.config.MailConfiguration;


@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender jms;
    
    @Value("$(spring.mail.username )")
    private String fromMail;
    
    @Autowired
    public MailServiceImpl(MailConfiguration mailConfiguration) {}

    @Override
    public void sendMail(String to, String subject, String body) {
        try {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom(fromMail);
            smm.setTo(to);
            smm.setSubject(subject);
            smm.setText(body);
            jms.send(smm);
            return;
        } catch(Exception e) {
            e.getStackTrace();
            System.out.println("GOOGLE_MAIL:- "+e.getMessage());
            return;
        }
    }
    
    
    
}
