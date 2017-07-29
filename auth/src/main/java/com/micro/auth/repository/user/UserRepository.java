package com.micro.auth.repository.user;

import com.micro.auth.domain.user.User;
import com.micro.auth.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for {@link com.micro.auth.domain.user.User}
 *
 * @see com.micro.auth.domain.user.User
 * @see org.springframework.data.jpa.repository.JpaRepository
 *
 * @author Sergey Bezvershenko
 */
public interface UserRepository extends BaseRepository<User> {
    /**
     * Locates the user based on the username. The search is case insensitive.

     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (Optional.empty() if not found)
     *
     * @see com.micro.auth.domain.user.User
     */
    @Query("select u from User u join fetch u.group where lower(u.username) = lower(:username)")
    Optional<User> findByUsername(@Param("username") String username);

    /**
     * Find user by email ignore case sensitive
     *
     * @param email the email identifying the user whose data is required.
     *
     * @return a fully populated user record (Optional.empty() if not found)
     *
     * @see com.micro.auth.domain.user.User
     */
    @Query("select u from User u join fetch u.group where lower(u.email) = lower(:email)")
    Optional<User> findByEmail(@Param("email") String email);
}
