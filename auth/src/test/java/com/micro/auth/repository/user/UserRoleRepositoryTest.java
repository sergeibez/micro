package com.micro.auth.repository.user;

import com.micro.auth.domain.user.UserRole;
import com.micro.auth.repository.BaseDataJpaTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link UserRoleRepository}
 *
 * @author Sergey Bezvershenko
 */
public class UserRoleRepositoryTest extends BaseDataJpaTest{
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void findOneShouldReturnUserRoleById() {
        UserRole role = userRoleRepository.getOne(0L);

        assertThat(role).isNotNull();
        assertThat(role.getId()).isEqualTo(0L);
        assertThat(role.getName()).isEqualTo("IS_AUTHENTICATED_ANONYMOUSLY");
    }

    @Test
    public void saveShouldStoreNewRoleToDatabase() {
        //  create a new role with the parent role "IS_AUTHENTICATED_ANONYMOUSLY"
        UserRole parent = userRoleRepository.getOne(0L);
        UserRole newRole = UserRole.builder().name("ROLE_MANAGER").parent(parent).note("Managers").build();

        //  save
        userRoleRepository.save(newRole);
        entityManager.flush();
        entityManager.clear();

        //  reload from db
        UserRole role = userRoleRepository.getOne(newRole.getId());

        // check
        assertThat(role).isNotNull();
        assertThat(role.getId()).isEqualTo(newRole.getId());
        assertThat(role.getName()).isEqualTo("ROLE_MANAGER");

        assertThat(role.getParent()).isNotNull();
        assertThat(role.getParent().getId()).isEqualTo(0L);
        assertThat(role.getParent().getName()).isEqualTo("IS_AUTHENTICATED_ANONYMOUSLY");
    }
}