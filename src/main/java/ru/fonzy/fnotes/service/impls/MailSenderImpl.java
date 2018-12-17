package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.service.MailSender;

@Service
public class MailSenderImpl implements MailSender {

    @Value("${spring.mail.username}")
    private String username;

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(@Qualifier("javaMailSenderConfig") JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String emailTo, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }
}
