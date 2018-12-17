package ru.fonzy.fnotes.service.impls;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.NoteDto;
import ru.fonzy.fnotes.dto.PasswordDto;
import ru.fonzy.fnotes.dto.ProfileDto;
import ru.fonzy.fnotes.dto.UserDto;
import ru.fonzy.fnotes.repository.UserRepository;
import ru.fonzy.fnotes.service.MailSender;
import ru.fonzy.fnotes.service.UserService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Value("${hostaddress}")
    private String hostAddress;

    private UserRepository userRepository;

    private MailSender mailSender;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user =  userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("user " + username + " already exists");

        return user;
    }

    @Override
    public Map<String, String> checkUserExists(UserDto userDto) {
        Map <String, String> userExistsErrors = new HashMap<>();
        User usernameCheck = userRepository.findByUsername(userDto.getUsername());
        User emailCheck = userRepository.findByEmail(userDto.getEmail());

        if (usernameCheck != null)
            userExistsErrors.put("username_exists", "Пользователь с таким логином уже существует");

        if (emailCheck != null)
            userExistsErrors.put("email_exists", "Такой почтовый адрес уже существвет");

        return userExistsErrors;

    }

    @Override
    public boolean addUser(UserDto userDto){
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), UUID.randomUUID().toString(), false, roles);

        String subject = "Подтвердите email";
        String text = String.format("Здравствуйте, %s! \n" +
                        "Для подтверждения вашего email перейдите пожалуйста по ссылке: " + hostAddress + "/activate/%s",
                user.getUsername(), user.getActivationCode());

        mailSender.send(user.getEmail(), subject, text);

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null)
            return false;

        user.setActivationCode(null);

        user.setEnabled(true);

        userRepository.save(user);

        return true;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(long id) {
        if (id == 1)
            return false;

        userRepository.deleteById(id);

        return true;
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId()).orElse(null);

        user.setUsername(userDto.getUsername());

        String newPassword = userDto.getPassword();
        if (!Strings.isNullOrEmpty(newPassword))
            user.setPassword(newPassword);

        user.setEnabled(userDto.isEnabled());

        user.setRoles(userDto.getRoles());

        userRepository.save(user);
    }

    @Override
    public void manage(long id, boolean enabled, Set<Role> userRoles) {
        User user = getUser(id);

        user.setEnabled(enabled);
        user.setRoles(userRoles);

        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(long id, String password) {
        User user = getUser(id);

        return user.getPassword().equals(password);
    }

    @Override
    public void updateUserProfile(ProfileDto profileDto) {
        User user = getUser(profileDto.getId());

        user.setEmail(profileDto.getEmail());

        userRepository.save(user);
    }

    @Override
    public void updateUserPassword(PasswordDto passwordDto) {
        User user = getUser(passwordDto.getId());

        if (!Strings.isNullOrEmpty(passwordDto.getPassword()))
            user.setPassword(passwordDto.getPassword());
    }
}
