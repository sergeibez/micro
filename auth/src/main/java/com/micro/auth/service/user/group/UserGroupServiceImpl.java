package com.micro.auth.service.user.group;

import com.micro.auth.domain.user.UserGroupRole;
import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.user.UserGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Implementation of {@link UserGroupService}
 *
 * @author Sergey Bezvershenko
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {
    private UserGroupRepository userGroupRepository;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    /**
     * Locate all roles of group by ID
     *
     * @param groupId group ID
     *
     * @return the list of roles
     *
     * @throws RuntimeException if the group could not be found by ID
     *
     * @see com.micro.auth.domain.user.UserRole
     * @see com.micro.auth.domain.user.UserGroup
     */
    @Override
    public List<UserRole> getGroupRoles(long groupId) {
        return userGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find UserGroup by id '%s'", groupId)))
                .getRoles().stream()
                .map(UserGroupRole::getRole)
                .flatMap(UserRole::getAllRoles)
                .collect(toList());
    }
}
