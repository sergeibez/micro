package com.micro.auth.service.user.user;

import com.micro.auth.domain.user.User;
import com.micro.auth.repository.user.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UserService}
 *
 * @author Sergey Bezvershenko
 */
public class UserServiceTest {
    private UserService userService;

    @Before
    public void setup() {
        User user = User.builder().id(1L).username("Test User").password("test1234").build();

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername("Test User")).thenReturn(user);
        when(userRepository.findByUsername("Test USER")).thenReturn(user);

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void findByUsernameShouldFindUserByNameIgnoreCase() throws Exception {
        assertThat(userService.findByUsername("Test User")).isNotNull();
        assertThat(userService.findByUsername("Test USER")).isNotNull();
    }

    @Test
    public void findByEmail() throws Exception {
    }

}