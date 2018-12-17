package ru.fonzy.fnotes.service;

public interface MailSender {

    void send(String emailTo, String subject, String text);
}
