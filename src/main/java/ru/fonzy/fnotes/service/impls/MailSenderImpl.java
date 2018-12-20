package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.service.MailSender;

import javax.mail.AuthenticationFailedException;

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
    public boolean send(String emailTo, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        try {
            javaMailSender.send(mailMessage);
        }
        catch (MailException e){
            e.printStackTrace();

            return false;
        }

        return true;

    }
}
