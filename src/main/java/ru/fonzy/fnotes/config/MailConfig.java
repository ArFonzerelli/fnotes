package ru.fonzy.fnotes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.validation.Valid;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.protocol}")
    private String protocol;


    @Bean(name = "javaMailSenderConfig")
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();

        javaMailSenderImpl.setHost(mailHost);
        javaMailSenderImpl.setPort(port);
        javaMailSenderImpl.setUsername(username);
        javaMailSenderImpl.setPassword(password);

        Properties properties = javaMailSenderImpl.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", protocol);

        return javaMailSenderImpl;
    }
}
