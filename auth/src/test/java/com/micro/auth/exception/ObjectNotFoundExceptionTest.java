package com.micro.auth.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ObjectNotFoundExceptionTest {
    @Test
    void objectNotFoundExceptionShouldHaveCorrespondingMessage() {
        assertThatThrownBy(() -> { throw new ObjectNotFoundException("Customer", 10L); } )
                .hasMessage("Cannot find object 'Customer' by ID '10'");
    }
}