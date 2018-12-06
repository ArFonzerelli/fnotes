package ru.fonzy.fnotes.service.impls;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.fonzy.fnotes.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user =  userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("user " + username + " already exists");

        return user;
    }

    public boolean addUser(UserDto userDto){

        User userInBase = userRepository.findByUsername(userDto.getUsername());

        if (userInBase != null){
            return false;
        }

        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), true, roles);

        userRepository.save(user);

        return true;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(long id) {
        if (id == 1)
            return false;

        userRepository.deleteById(id);

        return true;
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElse(null);
    }

//    public void updateUser(String id, String userName, String password, boolean enabled, Set<Role> userRoles){
//        User user = userRepository.findById(Long.valueOf(id)).orElse(null);
//
//        user.setUsername(userName);
//
//        if (!password.equals(""))
//            user.setPassword(password);
//
//        user.setEnabled(enabled);
//
//        user.setRoles(userRoles);
//
//        userRepository.save(user);
//    }

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
