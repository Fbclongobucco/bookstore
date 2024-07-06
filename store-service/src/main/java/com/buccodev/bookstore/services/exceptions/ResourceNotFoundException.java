package com.buccodev.bookstore.services.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    private static final long sereialVersionUId = 1L;

    public ResourceNotFoundException(Object id){
        super("Resource not found. Id " +id);
    }

}
