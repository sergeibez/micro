package com.micro.auth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Generate hash code for password
 *
 * @author Sergey Bezvershenko
 */
public class GeneratePasswordTest {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void generatePassword() {
        String password = "password1234";
        System.out.println(passwordEncoder.encode(password));
    }
}
