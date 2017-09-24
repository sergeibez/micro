package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Domain class for the linkage between {@link UserGroup} and {@link UserRole}
 *
 * @author Sergey Bezvershenko
 */
@Entity
@DynamicUpdate
public class UserGroupRole implements DomainEntity {
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
     * {@link UserGroup}
     */
    @ManyToOne
    @PrimaryKeyJoinColumn
    private UserGroup group;

    /**
     * {@link UserRole}
     */
    @ManyToOne
    @PrimaryKeyJoinColumn
    private UserRole role;

    /**
     * Some notes for the linkage
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Create Builder for UserGroupRole
     */
    public static UserGroupRoleBuilder builder() {
        return UserGroupRoleBuilder.anUserGroupRole();
    }

    /**
     * Builder for UserGroupRole
     */
    public static final class UserGroupRoleBuilder {
        private Long id;
        private Long version;
        private UserGroup group;
        private UserRole role;
        private String note = "";

        private UserGroupRoleBuilder() {
        }

        public static UserGroupRoleBuilder anUserGroupRole() {
            return new UserGroupRoleBuilder();
        }

        public UserGroupRoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserGroupRoleBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public UserGroupRoleBuilder group(UserGroup group) {
            this.group = group;
            return this;
        }

        public UserGroupRoleBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public UserGroupRoleBuilder note(String note) {
            this.note = note;
            return this;
        }

        public UserGroupRole build() {
            UserGroupRole userGroupRole = new UserGroupRole();
            userGroupRole.setId(id);
            userGroupRole.setVersion(version);
            userGroupRole.setGroup(group);
            userGroupRole.setRole(role);
            userGroupRole.setNote(note);
            return userGroupRole;
        }
    }
}
