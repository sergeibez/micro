package com.micro.auth.domain.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

/**
 * Provides core user information fro spring security.
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetails}
 *
 * @see org.springframework.security.core.userdetails.UserDetails
 * @see UserDetailsService
 *
 * @author Sergey Bezvershenko
 */
@Data
@Builder
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    /**
     * Unique ID
     */
    private long id;

    /**
     * The unique username used to authenticate the user
     */
    private String username;

    /**
     * The hash of the password used to authenticate the user.
     */
    private String password;

    /**
     * Email of User
     */
    @Builder.Default private String email = "";

    /**
     * Language of User in format en_En, de_De
     */
    @Builder.Default private String locale = "";

    /**
     * User account is enabled
     */
    @Builder.Default private boolean enabled = true;

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Builder.Default private boolean accountNonExpired = true;

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Builder.Default private boolean accountNonLocked = true;

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Builder.Default private boolean credentialsNonExpired = true;

    /**
     * The authorities granted to the user. Cannot be <code>null</code>.
     */
    @Builder.Default private Set<GrantedAuthority> authorities = new HashSet<>();
}
