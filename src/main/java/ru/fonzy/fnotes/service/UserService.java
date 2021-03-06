package ru.fonzy.fnotes.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.PasswordDto;
import ru.fonzy.fnotes.dto.ProfileDto;
import ru.fonzy.fnotes.dto.UserDto;

import java.util.Map;
import java.util.Set;

@Service
@Transactional
public interface UserService {

    UserDetails loadUserByUsername(String username);

    void addUserRegistered(UserDto userDto, String activationCode);

    Iterable<User> getAllUsers();

    User getUser(long id);

    boolean deleteUserById(long id);

    void updateUser(UserDto userDto);

    void manage(long id, boolean enabled, Set<Role> userRoles);

    boolean checkPassword(long id, String password);

    void updateUserProfile(ProfileDto profileDto);

    void updateUserPassword(PasswordDto passwordDto);

    Map<String,String> checkUserExists(UserDto userDto);

    User getUser(String email);

    void addPasswordRecoverCode(User user, String passwordRecoverCode);
}
