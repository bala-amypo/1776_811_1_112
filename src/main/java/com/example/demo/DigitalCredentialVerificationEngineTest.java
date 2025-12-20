package com.example.demo;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DigitalCredentialVerificationEngineTest {

    @Mock
    private UserRepository userRepository;

    private PasswordEncoder testPasswordEncoder = s -> s + "_ENC"; // simple encoder for tests

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, testPasswordEncoder);
    }

    // ----------------------
    // User Service Tests
    // ----------------------

    @Test
    public void testRegisterUserSuccess() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("1234");

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User savedUser = userService.registerUser(user);

        assertEquals(savedUser.getPassword(), "1234_ENC");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test(expectedExceptions = BadRequestException.class)
    public void testRegisterDuplicateEmail() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("1234");

        userService.registerUser(user);
    }

    @Test
    public void testFindByEmailSuccess() {
        User user = new User();
        user.setEmail("user@example.com");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        User foundUser = userService.findByEmail("user@example.com");
        assertEquals(foundUser.getEmail(), "user@example.com");
    }

    // ----------------------
    // You can add more tests for:
    // CredentialHolderProfileServiceImpl
    // CredentialRecordServiceImpl
    // VerificationRuleServiceImpl
    // VerificationRequestServiceImpl
    // AuditTrailServiceImpl
    // Controllers using Mockito + mockMvc if needed
    // ----------------------
}
