package com.cove.user.exception;

public class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
