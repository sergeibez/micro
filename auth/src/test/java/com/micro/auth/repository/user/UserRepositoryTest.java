package com.micro.auth.repository.user;

import com.micro.auth.domain.user.User;
import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.repository.BaseDataJpaTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link UserRepository}
 *
 * @author Sergey Bezvershenko
 */
public class UserRepositoryTest extends BaseDataJpaTest {
    @Autowired
    private UserRepository userRepository;

    private void createTestUser() {
        UserGroup group = UserGroup.builder().name("New group").build();
        User newUser = User.builder()
                .username("New user")
                .password("test1234")
                .email("test@email.com")
                .group(group)
                .build();

        this.entityManager.persist(group);
        this.entityManager.persist(newUser);
    }

    @Test
    public void findByUsernameShouldReturnUserIgnoreCaseSensitive() throws Exception {
        createTestUser();

        assertThat(userRepository.findByUsername("New user").getUsername()).isEqualTo("New user");
        assertThat(userRepository.findByUsername("New User").getUsername()).isEqualTo("New user");
        assertThat(userRepository.findByUsername("New USER").getUsername()).isEqualTo("New user");
    }

    @Test
    public void findByUsernameShouldReturnNullIfCannotFindUser() throws Exception {
        createTestUser();

        User user = userRepository.findByUsername("New userrrrrr");

        assertThat(user).isNull();
    }

    @Test
    public void findByEmailShouldReturnUserIgnoreCaseSensitive() throws Exception {
        createTestUser();

        assertThat(userRepository.findByEmail("test@email.com").getEmail()).isEqualTo("test@email.com");
        assertThat(userRepository.findByEmail("test@EMAIL.com").getEmail()).isEqualTo("test@email.com");
        assertThat(userRepository.findByEmail("test@eMAIL.com").getEmail()).isEqualTo("test@email.com");
    }

    @Test
    public void findByEmailShouldReturnNullIfCannotFindUser() throws Exception {
        createTestUser();

        User user = userRepository.findByEmail("testttttttttt@email.com");

        assertThat(user).isNull();
    }
}