package com.example.demo;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;

@Listeners(TestResultListener.class)
public class DigitalCredentialVerificationEngineTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);

        userRepository = mock(UserRepository.class);

        passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword + "_ENC";
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword + "_ENC");
            }
        };

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    public void registerUser_success() {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("1234");
        user.setRole("ADMIN");

        when(userRepository.existsByEmail("test@mail.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User saved = userService.registerUser(user);

        assert saved.getPassword().endsWith("_ENC");
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void registerUser_duplicateEmail() {
        User user = new User();
        user.setEmail("test@mail.com");

        when(userRepository.existsByEmail("test@mail.com")).thenReturn(true);

        userService.registerUser(user);
    }
}
