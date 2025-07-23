package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testMessage() {
        String message = "Resource not found!";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}
