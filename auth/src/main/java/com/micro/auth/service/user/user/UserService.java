package com.micro.auth.service.user.user;

import com.micro.auth.domain.user.User;

/**
 * Service for {@link User}
 *
 * @author Sergey Bezvershenko
 */
public interface UserService {
    /**
     * Locates the user based on the username. The search is case insensitive.

     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated User (can be <code>null</code> if not found)
     *
     * @see com.micro.auth.domain.user.User
     */
    User findByUsername(String username);

    /**
     * Find user by email ignore case sensitive
     *
     * @param email the email identifying the user whose data is required.
     *
     * @return a fully populated user (can be <code>null</code> if not found)
     *
     * @see com.micro.auth.domain.user.User
     */
    User findByEmail(String email);
}
