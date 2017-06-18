package com.micro.auth.repository.user;

import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.repository.base.BaseRepository;

/**
 * Repository for {@link com.micro.auth.domain.user.UserGroup}
 *
 * @see com.micro.auth.domain.user.UserGroup
 * @see org.springframework.data.jpa.repository.JpaRepository
 *
 * @author Sergey Bezvershenko
 */
public interface UserGroupRepository extends BaseRepository<UserGroup> {
}
