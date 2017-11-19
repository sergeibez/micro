package com.micro.auth.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ObjectNotFoundExceptionTest {
    @Test
    public void objectNotFoundExceptionShouldHaveCorrespondingMessage() {
        assertThatThrownBy(() -> { throw new ObjectNotFoundException("Customer", 10L); } )
                .hasMessage("Cannot find object 'Customer' by ID '10'");
    }
}