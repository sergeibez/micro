package com.micro.auth.domain.user;

import com.micro.auth.domain.base.DomainObject;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password", "group"})
public final class User implements DomainObject {
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
    @Builder.Default private boolean enabled = true;

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
    @Builder.Default private String email = "";

    /**
     * Language of User in format en_En, de_De
     */
    @Builder.Default private String locale = "";

    /**
     * Some description for user account
     */
    @Builder.Default private String note = "";

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
}
