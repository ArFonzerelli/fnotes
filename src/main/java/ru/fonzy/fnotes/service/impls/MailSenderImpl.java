package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.service.MailSender;

@Service
public class MailSenderImpl implements MailSender {

    private JavaMailSender javaMailSender;

    private boolean emailNotification;

    private String username;

    public MailSenderImpl(@Autowired @Qualifier("javaMailSenderConfig") JavaMailSender javaMailSender,
                          @Value("email_notification") String emailNotification,
                          @Value("spring.mail.username") String username) {
        this.javaMailSender = javaMailSender;
        this.emailNotification = Boolean.getBoolean(emailNotification);
        this.username = username;
    }

    @Override
    public boolean send(String emailTo, String subject, String text) {
        if (!emailNotification){
            System.out.println(text);
            return true;
        }

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
