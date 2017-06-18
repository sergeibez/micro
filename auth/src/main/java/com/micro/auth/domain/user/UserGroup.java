package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainObject;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserGroup implements DomainObject {
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
    @Builder.Default private String note = "";

    /**
     * The users which belong to the group
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "group")
    @PrimaryKeyJoinColumn
    @Singular private List<User> users;

    /**
     * The roles granted to the group
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "group")
    @PrimaryKeyJoinColumn
    @Singular private List<UserGroupRole> roles;
}
