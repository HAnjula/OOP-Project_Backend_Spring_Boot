package com.oop.websignup.POS.exception;

public class UserExistsExc extends RuntimeException {
    public UserExistsExc(String message) {
        super(message);
    }
}
