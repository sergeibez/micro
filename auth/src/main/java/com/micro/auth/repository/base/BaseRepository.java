package com.micro.auth.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base class for all repositories
 *
 * @author Sergey Bezvershenko
 */
@NoRepositoryBean
public interface BaseRepository<Entity> extends JpaRepository<Entity, Long> {
}
