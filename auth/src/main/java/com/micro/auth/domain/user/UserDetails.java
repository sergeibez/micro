package com.micro.auth.domain.user;

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
    private String email = "";

    /**
     * Language of User in format en_En, de_De
     */
    private String locale = "";

    /**
     * User account is enabled
     */
    private boolean enabled = true;

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    private boolean accountNonExpired = true;

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    private boolean accountNonLocked = true;

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    private boolean credentialsNonExpired = true;

    /**
     * The authorities granted to the user. Cannot be <code>null</code>.
     */
    private Set<GrantedAuthority> authorities = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * Create Builder for UserDetails
     */
    public static UserDetailsBuilder builder() {
        return UserDetailsBuilder.anUserDetails();
    }

    /**
     * Builder for UserDetails
     */
    public static final class UserDetailsBuilder {
        private long id;
        private String username;
        private String password;
        private String email = "";
        private String locale = "";
        private boolean enabled = true;
        private boolean accountNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean credentialsNonExpired = true;
        private Set<GrantedAuthority> authorities = new HashSet<>();

        private UserDetailsBuilder() {
        }

        public static UserDetailsBuilder anUserDetails() {
            return new UserDetailsBuilder();
        }

        public UserDetailsBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserDetailsBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDetailsBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDetailsBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDetailsBuilder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public UserDetailsBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserDetailsBuilder accountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public UserDetailsBuilder accountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public UserDetailsBuilder credentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public UserDetailsBuilder authorities(Set<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserDetails build() {
            UserDetails userDetails = new UserDetails();
            userDetails.setId(id);
            userDetails.setUsername(username);
            userDetails.setPassword(password);
            userDetails.setEmail(email);
            userDetails.setLocale(locale);
            userDetails.setEnabled(enabled);
            userDetails.setAccountNonExpired(accountNonExpired);
            userDetails.setAccountNonLocked(accountNonLocked);
            userDetails.setCredentialsNonExpired(credentialsNonExpired);
            userDetails.setAuthorities(authorities);
            return userDetails;
        }
    }
}
