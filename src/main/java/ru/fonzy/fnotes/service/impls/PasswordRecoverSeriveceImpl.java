package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.repository.UserRepository;
import ru.fonzy.fnotes.service.MailSender;
import ru.fonzy.fnotes.service.PasswordRecoverService;

@Service
public class PasswordRecoverSeriveceImpl implements PasswordRecoverService {

    @Value("${hostaddress}")
    private String hostAddress;

    private MailSender mailSender;

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean sendPasswordRecoverEmail(String email, String username, String recoverPasswordCode) {
        String subject = "Восстановление пароля";
        String text = String.format("Здравствуйте, %s! \n" +
                        "Для восстановления Вашего пароля перейдите пожалуйста по ссылке: " + hostAddress + "/recover/%s",
                username, recoverPasswordCode);


        return mailSender.send(email, subject, text);

    }

    @Override
    public User getUserByCode(String code) {
        User user = userRepository.findByPasswordRecoverCode(code);

        if (user == null)
            return null;

        user.setPasswordRecoverCode(null);

        userRepository.save(user);

        return user;
    }
}
