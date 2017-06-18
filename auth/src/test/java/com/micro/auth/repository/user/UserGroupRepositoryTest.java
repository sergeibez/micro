package com.micro.auth.repository.user;

import com.micro.auth.domain.user.UserGroup;
import com.micro.auth.repository.BaseDataJpaTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link UserGroupRepository}
 *
 * @author Sergey Bezvershenko
 */
public class UserGroupRepositoryTest extends BaseDataJpaTest {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Test
    public void getOneShouldReturnUserGroupById() {
        UserGroup group = userGroupRepository.getOne(0L);

        assertThat(group).isNotNull();
        assertThat(group.getId()).isEqualTo(0L);
        assertThat(group.getName()).isEqualTo("");
    }

    @Test
    public void saveShouldStoreNewUserGroupToDatabase() {
        //  create a new group
        UserGroup newGroup = UserGroup.builder().name("Managers").build();

        //  save
        userGroupRepository.save(newGroup);
        entityManager.flush();
        entityManager.clear();

        //  reload from db
        UserGroup group = userGroupRepository.getOne(newGroup.getId());

        // check
        assertThat(group).isNotNull();
        assertThat(group.getId()).isEqualTo(newGroup.getId());
        assertThat(group.getName()).isEqualTo("Managers");
    }
}