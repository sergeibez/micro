package com.micro.person.domain.user;

import com.micro.person.domain.base.DomainEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;

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
     * Unique user name
     */
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        private String username;
        private String note = "";

        private UserBuilder() {
        }

        private static UserBuilder anUser() {
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

        public UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
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
            user.setUsername(username);
            user.setNote(note);
            return user;
        }
    }
}
