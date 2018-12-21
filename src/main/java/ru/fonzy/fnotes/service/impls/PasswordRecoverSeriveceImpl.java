package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.repository.UserRepository;
import ru.fonzy.fnotes.service.MailSender;
import ru.fonzy.fnotes.service.PasswordRecoverService;

import java.util.Arrays;
import java.util.UUID;

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
    public String getPasswordRecoverCode() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean sendPasswordRecoverEmail(String email, String username, String recoverPasswordCode) {
//        String subject = "Восстановление пароля";
//        String text = String.format("Здравствуйте, %s! \n" +
//                        "Для восстановления Вашего пароля перейдите пожалуйста по ссылке: " + hostAddress + "/recover/%s",
//                username, recoverPasswordCode);
//
//
//           return mailSender.send(email, subject, text);
        System.out.println(hostAddress + "/recover/" + recoverPasswordCode);

        return true;
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
