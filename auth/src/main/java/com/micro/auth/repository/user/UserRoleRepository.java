package com.micro.auth.repository.user;

import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.base.BaseRepository;

/**
 * Repository for {@link com.micro.auth.domain.user.UserRole}
 *
 * @see com.micro.auth.domain.user.UserRole
 * @see org.springframework.data.jpa.repository.JpaRepository
 *
 * @author Sergey Bezvershenko
 */
public interface UserRoleRepository extends BaseRepository<UserRole> {
}
