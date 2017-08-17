package com.micro.auth.exception;

import java.util.function.Supplier;

/**
 * Thrown when Object cannot be found by ID
 *
 * @author Sergey Bezvershenko
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectName, Long id) {
        super(String.format("Cannot find object '%s' by ID '%s'", objectName, id));
    }

    /**
     * Create Supplier of exception
     * Use in lambda expressions
     *
     * @param objectName Object name
     * @param id         ID of object whith cannot be found
     *
     * @return supplier of exception
     */
    public static Supplier<ObjectNotFoundException> of(String objectName, Long id) {
        return () -> new ObjectNotFoundException(objectName, id);
    }
}
