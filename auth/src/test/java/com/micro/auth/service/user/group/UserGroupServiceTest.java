package com.micro.auth.service.user.group;

import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.domain.user.UserGroupRole;
import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.user.UserGroupRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UserGroupService}
 *
 * @author Sergey Bezvershenko
 */
public class UserGroupServiceTest {
    private UserGroupService userGroupService;

    @Before
    public void setup() {
        UserRole anonym = UserRole.builder().name("ROLE_ANONYM").build();
        UserRole staff = UserRole.builder().name("ROLE_STAFF").parent(anonym).build();
        UserRole admin = UserRole.builder().name("ROLE_ADMIN").parent(staff).build();

        UserGroup userGroup = UserGroup.builder()
                .role(UserGroupRole.builder().role(admin).build())
                .build();

        UserGroupRepository userGroupRepository = mock(UserGroupRepository.class);
        when(userGroupRepository.findById(0L)).thenReturn(Optional.of(userGroup));
        
        userGroupService = new UserGroupServiceImpl(userGroupRepository);
    }

    @Test
    public void getGroupRolesShouldReturnGroupRolesById() throws Exception {
        List<UserRole> roles = userGroupService.getGroupRoles(0L);

        assertThat(roles).isNotNull();
        assertThat(roles.stream().map(UserRole::getName).collect(Collectors.toList()))
                .containsExactlyInAnyOrder("ROLE_ANONYM", "ROLE_STAFF", "ROLE_ADMIN");
    }
}