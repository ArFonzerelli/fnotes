package ru.fonzy.fnotes.service;

public interface ActivationService {

    String getActivationCode();

    boolean sendActivationEmail(String email, String username, String activationCode);

    boolean activateUserByActivationCode(String code);
}
