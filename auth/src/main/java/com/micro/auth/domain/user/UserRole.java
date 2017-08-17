package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainObject;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.stream.Stream;

/**
 * User role
 *
 * @author Sergey Bezvershenko
 */
@Entity
@DynamicUpdate
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole implements DomainObject {
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
    @Builder.Default
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
}
