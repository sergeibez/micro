package com.micro.person.domain.person;

import com.micro.person.domain.base.DomainEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Domain class for Person information
 *
 * @author Sergey Bezvershenko
 */
@Entity
@DynamicUpdate
@Table(name = "users")
public class Person implements DomainEntity {
    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Record version
     */
    @Version
    private Long version;

    /**
     * First name
     */
    private String firstName;

    /**
     * Last name
     */
    private String lastName;

    /**
     * Birthday
     */
    private LocalDate birthday;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Create Builder for Person
     */
    public static PersonBuilder builder() {
        return PersonBuilder.aPerson();
    }

    /**
     * Builder for Person
     */
    public static final class PersonBuilder {
        private Long id;
        private Long version;
        private String firstName;
        private String lastName;
        private LocalDate birthday;

        private PersonBuilder() {
        }

        private static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PersonBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public PersonBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.setId(id);
            person.setVersion(version);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setBirthday(birthday);
            return person;
        }
    }
}
