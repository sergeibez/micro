package com.micro.auth.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ObjectNotFoundExceptionTest {
    @Rule
    public final ExpectedException expect = ExpectedException.none();

    @Test
    public void objectNotFoundExceptionShouldHaveCorrespondingMessage() {
        expect.expectMessage("Cannot find object 'Customer' by ID '10'");
        throw new ObjectNotFoundException("Customer", 10L);
    }
}