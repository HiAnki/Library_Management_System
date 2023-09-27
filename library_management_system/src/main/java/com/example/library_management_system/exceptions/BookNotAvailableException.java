package com.example.library_management_system.exceptions;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException(String message) {
        super(message);
    }
}
