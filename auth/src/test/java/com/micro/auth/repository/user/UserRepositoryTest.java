package com.micro.auth.repository.user;

import com.micro.auth.domain.user.User;
import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.repository.BaseDataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link UserRepository}
 *
 * @author Sergey Bezvershenko
 */
class UserRepositoryTest extends BaseDataJpaTest {
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
    void findByUsernameShouldReturnUserIgnoreCaseSensitive() throws Exception {
        createTestUser();

        assertThat(userRepository.findByUsername("New user").get().getUsername()).isEqualTo("New user");
        assertThat(userRepository.findByUsername("New User").get().getUsername()).isEqualTo("New user");
        assertThat(userRepository.findByUsername("New USER").get().getUsername()).isEqualTo("New user");
    }

    @Test
    void findByUsernameShouldReturnEmptyOptionalIfCannotFindUser() throws Exception {
        createTestUser();

        Optional<User> user = userRepository.findByUsername("New userrrrrr");

        assertThat(user.isPresent()).isFalse();
    }

    @Test
    void findByEmailShouldReturnUserIgnoreCaseSensitive() throws Exception {
        createTestUser();

        assertThat(userRepository.findByEmail("test@email.com").get().getEmail()).isEqualTo("test@email.com");
        assertThat(userRepository.findByEmail("test@EMAIL.com").get().getEmail()).isEqualTo("test@email.com");
        assertThat(userRepository.findByEmail("test@eMAIL.com").get().getEmail()).isEqualTo("test@email.com");
    }

    @Test
    void findByEmailShouldReturnEmptyOptionalIfCannotFindUser() throws Exception {
        createTestUser();

        Optional<User> user = userRepository.findByEmail("testttttttttt@email.com");

        assertThat(user.isPresent()).isFalse();
    }
}