package com.micro.auth.service.user.user;

import com.micro.auth.domain.user.User;
import com.micro.auth.repository.user.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UserService}
 *
 * @author Sergey Bezvershenko
 */
public class UserServiceTest {
    private UserService userService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        User user = User.builder()
                .id(1L)
                .username("Test User")
                .password("test1234")
                .email("test@email.com")
                .build();

        when(userRepository.findByUsername("Test User")).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("Test USER")).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("Unknown USER")).thenReturn(Optional.empty());

        when(userRepository.findByEmail("test@email.com")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("Test@Email.CoM")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("unknown@email.com")).thenReturn(Optional.empty());

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void findByUsernameShouldFindUserByNameIgnoreCase() throws Exception {
        assertTrue(userService.findByUsername("Test User").isPresent());
        assertTrue(userService.findByUsername("Test USER").isPresent());

        assertFalse(userService.findByUsername("Unknown USER").isPresent());
    }

    @Test
    public void findByEmailShouldFindUserByEmailIgnoreCase() throws Exception {
        assertTrue(userService.findByEmail("test@email.com").isPresent());
        assertTrue(userService.findByEmail("Test@Email.CoM").isPresent());

        assertFalse(userService.findByEmail("unknown@email.com").isPresent());
    }
}