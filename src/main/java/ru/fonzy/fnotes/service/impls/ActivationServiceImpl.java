package ru.fonzy.fnotes.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.repository.UserRepository;
import ru.fonzy.fnotes.service.ActivationService;
import ru.fonzy.fnotes.service.CodeGeneratorService;
import ru.fonzy.fnotes.service.MailSender;

import java.util.UUID;

@Service
public class ActivationServiceImpl implements ActivationService {

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
    public boolean sendActivationEmail(String email, String username, String activationCode) {
        String subject = "Подтвердите email";
        String text = String.format("Здравствуйте, %s! \n" +
                        "Для подтверждения вашего email перейдите пожалуйста по ссылке: " + hostAddress + "/activate/%s",
                username, activationCode);


        return mailSender.send(email, subject, text);

    }

    @Override
    public boolean activateUserByActivationCode(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null)
            return false;

        user.setActivationCode(null);

        user.setEnabled(true);

        userRepository.save(user);

        return true;
    }

}
