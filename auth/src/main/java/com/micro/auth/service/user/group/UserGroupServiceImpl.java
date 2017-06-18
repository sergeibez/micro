package com.micro.auth.service.user.group;

import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.user.UserGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

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
        List<UserRole> roles = new ArrayList<>();

        UserGroup group = userGroupRepository.findOne(groupId);

        Assert.notNull(group, String.format("Cannot find UserGroup by id '%s'", groupId));

        group.getRoles().forEach(it -> addRole(roles, it.getRole()));

        return roles;
    }

    /**
     * Recursive method. Add role to list and add roles from parent if parent is not <code>null</code>
     *
     * @param roles a list of roles
     * @param role a role for adding to list
     */
    private void addRole(List<UserRole> roles, UserRole role) {
        roles.add(role);
        if (role.getParent() != null) {
            addRole(roles, role.getParent());
        }
    }
}
