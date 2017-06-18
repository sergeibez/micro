package com.micro.auth.converter.user;

import com.micro.auth.domain.user.User;
import com.micro.auth.domain.user.UserDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Converter from {@link User} to ${@link UserDetails}
 *
 * @see User
 * @see UserDetails
 * @see Converter
 *
 * @author Sergey Bezvershenko
 */
@Component
public class UserDetailsConverter implements Converter<User, UserDetails> {
    /**
     * Convert User to UserDetails
     */
    @Override
    public UserDetails convert(User user) {
        Assert.notNull(user, "Parameter 'user' must not be null");
        Assert.notNull(user.getId(), "user.id must not be null");

        return UserDetails.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())

                .enabled(user.isEnabled())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())

                .build();
    }
}
