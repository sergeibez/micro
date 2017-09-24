package com.micro.auth.service.user.group;

import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.domain.user.UserGroupRole;
import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.user.UserGroupRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UserGroupService}
 *
 * @author Sergey Bezvershenko
 */
public class UserGroupServiceTest {
    private UserGroupService userGroupService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private UserGroupRepository userGroupRepository;


    @Before
    public void setup() {
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
    public void getGroupRolesShouldReturnGroupRolesById() throws Exception {
        List<UserRole> roles = userGroupService.getGroupRoles(0L);

        assertThat(roles).isNotNull();
        assertThat(roles.stream().map(UserRole::getName).collect(toList()))
                .containsExactlyInAnyOrder("ROLE_ANONYM", "ROLE_STAFF", "ROLE_ADMIN");
    }
}