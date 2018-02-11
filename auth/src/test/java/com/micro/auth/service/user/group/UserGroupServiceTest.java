package com.micro.auth.service.user.group;

import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.domain.user.UserGroupRole;
import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.user.UserGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Test for {@link UserGroupService}
 *
 * @author Sergey Bezvershenko
 */
class UserGroupServiceTest {
    private UserGroupService userGroupService;

    @Mock
    private UserGroupRepository userGroupRepository;

    @BeforeEach
    void setup() {
        initMocks(this);

        UserRole anonym = UserRole.builder().name("ROLE_ANONYM").build();
        UserRole staff = UserRole.builder().name("ROLE_STAFF").parent(anonym).build();
        UserRole admin = UserRole.builder().name("ROLE_ADMIN").parent(staff).build();

        UserGroup userGroup = UserGroup.builder()
                .role(UserGroupRole.builder().role(admin).build())
                .build();

        when(userGroupRepository.findById(0L)).thenReturn(Optional.of(userGroup));
        
        userGroupService = new UserGroupServiceImpl(userGroupRepository);
    }

    @Test
    void getGroupRolesShouldReturnGroupRolesById() throws Exception {
        List<UserRole> roles = userGroupService.getGroupRoles(0L);

        assertThat(roles).isNotNull();
        assertThat(roles.stream().map(UserRole::getName).collect(toList()))
                .containsExactlyInAnyOrder("ROLE_ANONYM", "ROLE_STAFF", "ROLE_ADMIN");
    }
}