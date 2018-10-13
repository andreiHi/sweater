package com.example.sweater.service;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.10.2018.
 * @version $Id$.
 * @since 0.1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("some@some.com");
        boolean isUserCreated = userService.addUser(user);

        assertTrue(isUserCreated);
        assertNotNull(user.getActivationCode());
        assertTrue(is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        verify(userRepository, times(1)).save(user);
        verify(mailSender, times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    public void addFailTest() {
        User user = new User();
        user.setUsername("John");

        doReturn(new User())
                .when(userRepository)
                .findByUsername("John");

        boolean addUser = userService.addUser(user);
        assertFalse(addUser);
        verify(userRepository, times(0)).save(any(User.class));
        verify(mailSender, times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    public void activateUser() {
        User user = new User();
        user.setActivationCode("bingo@!");
        doReturn(user)
                .when(userRepository)
                .findByActivationCode("activate");
        boolean isUserActivated = userService.activateUser("activate");
        assertTrue(isUserActivated);
        assertNull(user.getActivationCode());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = userService.activateUser("activate");
        assertFalse(isUserActivated);
        verify(userRepository, times(0)).save(any(User.class));
    }
}