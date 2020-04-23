package com.ivan.CT.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MyMailSender {
    private final static Logger logger = LoggerFactory.getLogger(MyMailSender.class);

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public MyMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setTo(to);

        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            // мол типа говорит, что my application is not safety;
            // да оно безопаснее всех, я тебе отвечаю!
            // google не пропускает!
            logger.error("LOGGER: catch " + e + " in " + RegistrationService.class.getName());
            return false;
        }
        logger.info("LOGGER: message 'accept registration' sent to user!");
        return true;
    }
}
