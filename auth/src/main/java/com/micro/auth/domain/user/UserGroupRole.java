package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainObject;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Domain class for the linkage between {@link UserGroup} and {@link UserRole}
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
public class UserGroupRole implements DomainObject {
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
    @Builder.Default private String note = "";
}
