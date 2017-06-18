package com.micro.auth.service.user.user;

import com.micro.auth.domain.user.User;
import com.micro.auth.repository.user.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserService
 *
 * @author Sergey Bezvershenko
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Locates the user based on the username. The search is case insensitive.

     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated User (can be <code>null</code> if not found)
     *
     * @see com.micro.auth.domain.user.User
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find user by email ignore case sensitive
     *
     * @param email the email identifying the user whose data is required.
     *
     * @return a fully populated user (can be <code>null</code> if not found)
     *
     * @see com.micro.auth.domain.user.User
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
