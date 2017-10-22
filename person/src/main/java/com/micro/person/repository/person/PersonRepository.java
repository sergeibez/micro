package com.micro.person.repository.person;

import com.micro.person.domain.person.Person;
import com.micro.person.repository.base.BaseRepository;

/**
 * Repository for {@link com.micro.person.domain.person.Person}
 *
 * @see com.micro.person.domain.person.Person
 * @see org.springframework.data.jpa.repository.JpaRepository
 *
 * @author Sergey Bezvershenko
 */
public interface PersonRepository extends BaseRepository<Person> {
}
