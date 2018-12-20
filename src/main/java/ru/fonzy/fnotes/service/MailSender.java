package ru.fonzy.fnotes.service;

public interface MailSender {

    boolean send(String emailTo, String subject, String text);
}
