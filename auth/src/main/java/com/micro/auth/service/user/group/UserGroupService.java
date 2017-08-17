package com.micro.auth.service.user.group;

import com.micro.auth.domain.user.UserRole;
import com.micro.auth.exception.ObjectNotFoundException;

import java.util.List;

/**
 * Service for {@link com.micro.auth.domain.user.UserGroup}
 *
 * @author Sergey Bezvershenko
 */
public interface UserGroupService {
    /**
     * Locate all roles of group by ID
     *
     * @param groupId group ID
     *
     * @return the list of roles
     *
     * @throws ObjectNotFoundException if the group could not be found by ID

     * @see com.micro.auth.domain.user.UserRole
     * @see com.micro.auth.domain.user.UserGroup
     */
    List<UserRole> getGroupRoles(long groupId);
}
