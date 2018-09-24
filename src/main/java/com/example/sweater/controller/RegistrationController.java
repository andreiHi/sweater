package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.09.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Controller
public class RegistrationController {

    private  final UserRepository userRepo;

    @Autowired
    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration () {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object>map) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            map.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
