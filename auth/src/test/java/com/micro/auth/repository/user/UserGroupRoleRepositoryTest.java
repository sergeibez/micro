package com.micro.auth.repository.user;

import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.domain.user.UserGroupRole;
import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.BaseDataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link UserGroupRoleRepository}
 *
 * @author Sergey Bezvershenko
 */
class UserGroupRoleRepositoryTest extends BaseDataJpaTest {
    @Autowired
    private UserGroupRoleRepository userGroupRoleRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    void getOneShouldReturnUserGroupRoleById() {
        UserGroupRole groupRole = userGroupRoleRepository.getOne(1L);

        assertThat(groupRole).isNotNull();
        assertThat(groupRole.getId()).isEqualTo(1L);

        assertThat(groupRole.getGroup().getId()).isEqualTo(1L);
        assertThat(groupRole.getGroup().getName()).isEqualTo("Developer");

        assertThat(groupRole.getRole().getId()).isEqualTo(1L);
        assertThat(groupRole.getRole().getName()).isEqualTo("ROLE_DEVELOPER");
    }

    @Test
    void saveShouldStoreNewLinkageBetweenUserGroupAndRoleToDatabase() {
        //  create a new linkage between group and role
        UserGroup group = userGroupRepository.getOne(1L);
        UserRole newRole = UserRole.builder().name("ROLE_MANAGER").note("Managers").build();
        UserGroupRole newGroupRole = UserGroupRole.builder().group(group).role(newRole).build();

        //  save
        userRoleRepository.save(newRole);
        long newId = userGroupRoleRepository.save(newGroupRole).getId();
        
        entityManager.flush();
        entityManager.clear();

        //  reload from db
        UserGroupRole groupRole = userGroupRoleRepository.getOne(newId);

        // check
        assertThat(groupRole).isNotNull();
        assertThat(groupRole.getId()).isEqualTo(newId);
        assertThat(groupRole.getGroup().getId()).isEqualTo(1L);
        assertThat(groupRole.getRole().getName()).isEqualTo("ROLE_MANAGER");
    }
}