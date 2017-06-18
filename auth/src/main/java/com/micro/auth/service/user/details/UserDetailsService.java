package com.micro.auth.service.user.details;

import com.micro.auth.domain.user.User;
import com.micro.auth.domain.user.UserDetails;
import com.micro.auth.domain.user.UserRole;
import com.micro.auth.service.user.group.UserGroupService;
import com.micro.auth.service.user.user.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of spring <code>UserDetailsService</code>
 * It should implement <code>UserDetailsService.loadUserByUsername</code>
 *
 * @see org.springframework.security.core.userdetails.UserDetailsService
 *
 * @author Sergey Bezvershenko
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserService userService;
    private UserGroupService userGroupService;
    private ConversionService conversionService;

    public UserDetailsService(UserService userService, UserGroupService userGroupService,
            ConversionService conversionService) {
        this.userService = userService;
        this.userGroupService = userGroupService;
        this.conversionService = conversionService;
    }

    /**
     * Locates the user based on the username. The search is case insensitive.
     *
     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     *
     * @see com.micro.auth.domain.user.UserDetails
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Cannot find User '%s'.", username));
        }

        UserDetails userDetails = conversionService.convert(user, UserDetails.class);
        List<UserRole> roles = userGroupService.getGroupRoles(user.getGroup().getId());

        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        userDetails.getAuthorities().addAll(authorities);

        return userDetails;
    }
}
