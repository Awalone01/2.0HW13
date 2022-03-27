package com.company.exception;

public class WrongIndexException extends RuntimeException {
    public WrongIndexException(String message) {
        super(message);
    }
}
