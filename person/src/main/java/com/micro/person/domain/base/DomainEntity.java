package com.micro.person.domain.base;

/**
 * Base interface for Domain classes
 *
 * @author Sergey Bezvershenko
 */
public interface DomainEntity {
    /**
     * Unique ID
     */
    Long getId();
    void setId(Long id);

    /**
     * Record version in database
     * It is used for an optimistic locking
     */
    Long getVersion();
    void setVersion(Long version);
}
