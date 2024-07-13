package com.buccodev.bookstore.services.exceptions;



public class ResourceNotFoundException extends RuntimeException {

    private static final long sereialVersionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Resource "+id+" not found!");

    }

}
