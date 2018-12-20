package ru.fonzy.fnotes.service;

public interface PasswordRecoverService {

    String getPasswordRecoverCode();

    boolean sendPasswordRecoverEmail(String email, String username, String activationCode);

    boolean recoverPasswordByRecoverCode(String code);


}
