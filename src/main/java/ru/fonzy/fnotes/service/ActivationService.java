package ru.fonzy.fnotes.service;

public interface ActivationService extends CodeGeneratorService{

    boolean sendActivationEmail(String email, String username, String activationCode);

    boolean activateUserByActivationCode(String code);
}
