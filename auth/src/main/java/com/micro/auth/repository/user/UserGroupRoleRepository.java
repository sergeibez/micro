package com.micro.auth.repository.user;

import com.micro.auth.domain.user.UserGroupRole;
import com.micro.auth.repository.base.BaseRepository;

/**
 * Repository for {@link com.micro.auth.domain.user.UserGroupRole}
 *
 * @see com.micro.auth.domain.user.UserGroupRole
 * @see org.springframework.data.jpa.repository.JpaRepository
 *
 * @author Sergey Bezvershenko
 */
public interface UserGroupRoleRepository extends BaseRepository<UserGroupRole> {
}
