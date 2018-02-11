package com.micro.auth.config.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A password encoder that does nothing. Work with plain text passwords.
 * Use for basic authorization
 *
 * @author Sergey Bezvershenko
 */
public class PlainTextPasswordEncoder implements PasswordEncoder {
    private static final PasswordEncoder INSTANCE = new PlainTextPasswordEncoder();

    /**
     * Get the singleton {@link PlainTextPasswordEncoder}.
     */
    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private PlainTextPasswordEncoder() {
    }

    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
