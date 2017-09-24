package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.stream.Stream;

/**
 * User role
 *
 * @author Sergey Bezvershenko
 */
@Entity
@DynamicUpdate
public class UserRole implements DomainEntity {
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
     * Unique role name
     */
    private String name;

    /**
     * Description of the role
     */
    private String note = "";

    /**
     * Parent role
     * The UserRole inherits recursively the parent roles: for example
     * ROLE_ADMIN inherits from ROLE_STAFF and ROLE_STAFF inherits from IS_AUTHENTICATED_ANONYMOUSLY
     */
    @ManyToOne
    @PrimaryKeyJoinColumn
    private UserRole parent;

    /**
     * Return this role and all parents roles
     * 
     * @return This role and all parents roles
     *
     * @author Sergey Bezvershenko
     */
    public Stream<UserRole> getAllRoles() {
        return parent == null ? Stream.of(this) : Stream.concat(Stream.of(this), parent.getAllRoles());
    }

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

    public UserRole getParent() {
        return parent;
    }

    public void setParent(UserRole parent) {
        this.parent = parent;
    }

    /**
     * Create Builder for UserRole
     */
    public static UserRoleBuilder builder() {
        return UserRoleBuilder.anUserRole();
    }

    /**
     * Builder for UserRole
     */
    public static final class UserRoleBuilder {
        private Long id;
        private Long version;
        private String name;
        private String note = "";
        private UserRole parent;

        private UserRoleBuilder() {
        }

        public static UserRoleBuilder anUserRole() {
            return new UserRoleBuilder();
        }

        public UserRoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserRoleBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public UserRoleBuilder name(String name) {
            Assert.notNull(name, "name cannot be null");
            this.name = name;
            return this;
        }

        public UserRoleBuilder note(String note) {
            this.note = note;
            return this;
        }

        public UserRoleBuilder parent(UserRole parent) {
            this.parent = parent;
            return this;
        }

        public UserRole build() {
            UserRole userRole = new UserRole();
            userRole.setId(id);
            userRole.setVersion(version);
            userRole.setName(name);
            userRole.setNote(note);
            userRole.setParent(parent);
            return userRole;
        }
    }
}
