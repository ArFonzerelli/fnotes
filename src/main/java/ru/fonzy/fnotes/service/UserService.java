package ru.fonzy.fnotes.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.dto.UserDto;

import java.util.Set;

@Service
@Transactional
public interface UserService {

    UserDetails loadUserByUsername(String username);

    boolean addUser(UserDto userDto);

    Iterable<User> getAllUsers();

    User getUser(long id);

    boolean deleteUserById(long id);

    void updateUser(UserDto userDto);

    void manage(long id, boolean enabled, Set<Role> userRoles);
}
