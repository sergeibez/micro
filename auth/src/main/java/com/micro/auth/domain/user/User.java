package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Domain class for User account information
 *
 * @author Sergey Bezvershenko
 */
@Entity
@DynamicUpdate
@Table(name = "users")
public final class User implements DomainEntity {
    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Record version
     */
    @Version
    private Long version;

    /**
     * Group of which belongs to the user
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserGroup group;

    /**
     * Unique user name
     */
    private String username;

    /**
     * Hash of the passwords
     */
    private String password;

    /**
     * User account is enabled
     */
    private boolean enabled = true;

    /**
     * The time until an account is locked
     */
    private LocalDateTime accountLockedUntil;

    /**
     * The time when account is expired
     */
    private LocalDateTime accountExpireDateTime;

    /**
     * The time when credentials are expired
     */
    private LocalDateTime credentialsExpireDateTime;

    /**
     * Email of User
     */
    private String email = "";

    /**
     * Language of User in format en_En, de_De
     */
    private String locale = "";

    /**
     * Some description for user account
     */
    private String note = "";

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getAccountLockedUntil() {
        return accountLockedUntil;
    }

    public void setAccountLockedUntil(LocalDateTime accountLockedUntil) {
        this.accountLockedUntil = accountLockedUntil;
    }

    public LocalDateTime getAccountExpireDateTime() {
        return accountExpireDateTime;
    }

    public void setAccountExpireDateTime(LocalDateTime accountExpireDateTime) {
        this.accountExpireDateTime = accountExpireDateTime;
    }

    public LocalDateTime getCredentialsExpireDateTime() {
        return credentialsExpireDateTime;
    }

    public void setCredentialsExpireDateTime(LocalDateTime credentialsExpireDateTime) {
        this.credentialsExpireDateTime = credentialsExpireDateTime;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    public boolean isAccountNonExpired() {
        return accountExpireDateTime == null || accountExpireDateTime.isAfter(LocalDateTime.now());
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    public boolean isAccountNonLocked() {
        return accountLockedUntil == null || accountLockedUntil.isBefore(LocalDateTime.now());
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    public boolean isCredentialsNonExpired() {
        return credentialsExpireDateTime == null || credentialsExpireDateTime.isAfter(LocalDateTime.now());
    }

    /**
     * Create Builder for User
     */
    public static UserBuilder builder() {
        return UserBuilder.anUser();
    }

    /**
     * User Builder
     */
    public static final class UserBuilder {
        private Long id;
        private Long version;
        private UserGroup group;
        private String username;
        private String password;
        private boolean enabled = true;
        private LocalDateTime accountLockedUntil;
        private LocalDateTime accountExpireDateTime;
        private LocalDateTime credentialsExpireDateTime;
        private String email = "";
        private String locale = "";
        private String note = "";

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public UserBuilder group(UserGroup group) {
            this.group = group;
            return this;
        }

        public UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public UserBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserBuilder accountLockedUntil(LocalDateTime accountLockedUntil) {
            this.accountLockedUntil = accountLockedUntil;
            return this;
        }

        public UserBuilder accountExpireDateTime(LocalDateTime accountExpireDateTime) {
            this.accountExpireDateTime = accountExpireDateTime;
            return this;
        }

        public UserBuilder credentialsExpireDateTime(LocalDateTime credentialsExpireDateTime) {
            this.credentialsExpireDateTime = credentialsExpireDateTime;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public UserBuilder note(String note) {
            this.note = note;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setVersion(version);
            user.setGroup(group);
            user.setUsername(username);
            user.setPassword(password);
            user.setEnabled(enabled);
            user.setAccountLockedUntil(accountLockedUntil);
            user.setAccountExpireDateTime(accountExpireDateTime);
            user.setCredentialsExpireDateTime(credentialsExpireDateTime);
            user.setEmail(email);
            user.setLocale(locale);
            user.setNote(note);
            return user;
        }
    }
}
