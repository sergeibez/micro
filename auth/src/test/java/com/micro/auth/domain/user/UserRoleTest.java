package com.micro.auth.domain.user;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link UserRole}
 *
 * @author Sergey Bezvershenko
 */
class UserRoleTest {
    @Test
    void getAllRolesShouldReturnRoleAndAllParentsRoles() throws Exception {
        UserRole anonymous = UserRole.builder().id(0L).name("ROLE_ANONYMOUS").build();
        UserRole customer = UserRole.builder().id(1L).name("ROLE_CUSTOMER").parent(anonymous).build();
        UserRole guest = UserRole.builder().id(3L).name("ROLE_GUEST").parent(customer).build();

        assertThat(guest.getAllRoles().map(UserRole::getName).collect(toList()))
                .containsExactlyInAnyOrder("ROLE_GUEST", "ROLE_CUSTOMER", "ROLE_ANONYMOUS");
    }
}