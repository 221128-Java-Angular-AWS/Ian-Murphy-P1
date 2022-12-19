package com.revature.exceptions;

public class UserExistsException extends Exception {
    public UserExistsException(String msg) {
        super(msg);
    }
}

//Completion Checklist #3: Will notify the user if the username is unavailable