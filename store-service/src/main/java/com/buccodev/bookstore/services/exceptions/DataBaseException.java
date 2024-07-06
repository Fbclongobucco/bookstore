package com.buccodev.bookstore.services.exceptions;

public class DataBaseException extends RuntimeException{

    private static final long sereialVersionUId = 1L;

    public DataBaseException(String msg){
        super(msg);
    }
}
