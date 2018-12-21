package ru.fonzy.fnotes.service;

import ru.fonzy.fnotes.domain.User;

public interface PasswordRecoverService extends CodeGeneratorService {

    boolean sendPasswordRecoverEmail(String email, String username, String activationCode);

    User getUserByCode(String code);
}
