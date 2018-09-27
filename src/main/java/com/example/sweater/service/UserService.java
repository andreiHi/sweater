package com.example.sweater.service;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.09.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Component
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final MailSender mailSender;

    @Value("${develop.path}")
    private String developPath;

    public UserService(UserRepository userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public boolean addUser(User user) {
        boolean found = false;
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb == null) {
            found = true;
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setActivationCode(UUID.randomUUID().toString());
            userRepo.save(user);
            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format("Hello, %s! \n" +
                            "Welcome to Sweater. Please visit next link: %s/activate/%s",
                      user.getUsername(),
                      developPath,
                      user.getActivationCode()
                );
                this.mailSender.send(user.getEmail(), "Activation code", message);
            }

        }
        return found;
    }

    public boolean activateUser(String code) {
        boolean found = false;
        User user = userRepo.findByActivationCode(code);
        if (user != null) {
            found = true;
            user.setActivationCode(null);
            userRepo.save(user);
        }
        return  found;
    }
}
