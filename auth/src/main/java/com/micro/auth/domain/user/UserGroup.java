package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain class for User group
 * The set of users and their roles
 *
 * @see User
 * @see UserRole
 */
@Entity
@DynamicUpdate
public class UserGroup implements DomainEntity {
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
     * Unique group name
     */
    private String name;

    /**
     * Some notes about group
     */
    private String note = "";

    /**
     * The users which belong to the group
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "group")
    @PrimaryKeyJoinColumn
    private List<User> users;

    /**
     * The roles granted to the group
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "group")
    @PrimaryKeyJoinColumn
    private List<UserGroupRole> roles;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UserGroupRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserGroupRole> roles) {
        this.roles = roles;
    }

    /**
     * Create Builder for UserGroup
     */
    public static UserGroupBuilder builder() {
        return UserGroupBuilder.anUserGroup();
    }

    /**
     * Builder for UserGroup
     */

    public static final class UserGroupBuilder {
        private Long id;
        private Long version;
        private String name;
        private String note = "";
        private List<User> users = new ArrayList<>();
        private List<UserGroupRole> roles = new ArrayList<>();

        private UserGroupBuilder() {
        }

        public static UserGroupBuilder anUserGroup() {
            return new UserGroupBuilder();
        }

        public UserGroupBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserGroupBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public UserGroupBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserGroupBuilder note(String note) {
            this.note = note;
            return this;
        }

        public UserGroupBuilder users(List<User> users) {
            this.users = users;
            return this;
        }

        public UserGroupBuilder roles(List<UserGroupRole> roles) {
            this.roles = roles;
            return this;
        }

        public UserGroupBuilder role(UserGroupRole role) {
            this.roles.add(role);
            return this;
        }

        public UserGroup build() {
            UserGroup userGroup = new UserGroup();
            userGroup.setId(id);
            userGroup.setVersion(version);
            userGroup.setName(name);
            userGroup.setNote(note);
            userGroup.setUsers(users);
            userGroup.setRoles(roles);
            return userGroup;
        }
    }
}
