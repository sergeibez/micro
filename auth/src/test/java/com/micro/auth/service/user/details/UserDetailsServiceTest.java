package com.micro.auth.service.user.details;

import com.micro.auth.converter.user.UserDetailsConverter;
import com.micro.auth.domain.user.*;
import com.micro.auth.service.user.group.UserGroupService;
import com.micro.auth.service.user.user.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UserDetailsService}
 *
 * @author Sergey Bezvershenko
 */
public class UserDetailsServiceTest {
    private UserDetailsService userDetailsService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private UserService userService;

    @Mock
    private UserGroupService userGroupService;

    @Before
    public void setup() {
        UserRole staff = UserRole.builder().name("ROLE_STAFF").build();
        UserRole admin = UserRole.builder().name("ROLE_ADMIN").parent(staff).build();

        List<UserRole> roles = Arrays.asList(staff, admin);

        UserGroup group = UserGroup.builder()
                .id(1L)
                .name("Administrators")
                .role(UserGroupRole.builder().role(admin).build())
                .build();

        User user = User.builder()
                .id(1L).username("Test User")
                .password("test1234")
                .group(group)
                .build();

        when(userService.findByUsername("Test User")).thenReturn(Optional.of(user));
        
        when(userGroupService.getGroupRoles(1L)).thenReturn(roles);

        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new UserDetailsConverter());

        userDetailsService = new UserDetailsService(userService, userGroupService, conversionService);
    }

    @Test
    public void loadUserByUsername() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("Test User");

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList()))
                .containsExactlyInAnyOrder("ROLE_STAFF", "ROLE_ADMIN");
    }
}