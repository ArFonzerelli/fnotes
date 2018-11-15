package ru.fonzy.fnotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.fonzy.fnotes.domain.Role;
import ru.fonzy.fnotes.domain.User;
import ru.fonzy.fnotes.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){

        User userInBase = (User) loadUserByUsername(user.getUsername());

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
}
