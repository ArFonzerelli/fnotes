package ru.fonzy.fnotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user =  userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("user " + username + " already exists");

        return user;
    }

    public boolean addUser(User user){

        User userInBase = userRepository.findByUsername(user.getUsername());

        if (userInBase != null){
            return false;
        }

        user.setEnabled(true);

        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);

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

    public void updateUser(String id, String userName, String password, boolean enabled, Set<Role> userRoles){
        User user = userRepository.findById(Long.valueOf(id)).orElse(null);

        user.setUsername(userName);

        if (!password.equals(""))
            user.setPassword(password);

        user.setEnabled(enabled);

        user.setRoles(userRoles);

        userRepository.save(user);
    }

}
