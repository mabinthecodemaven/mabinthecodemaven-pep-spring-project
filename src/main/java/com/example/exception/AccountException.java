package com.example.exception;

public class AccountException {

    public static class DuplicateUsernameException extends RuntimeException {
        public DuplicateUsernameException(String username) {
            super(String.format("%s already in use", username));
        }
    }

    public static class InvalidAccountException extends RuntimeException{
        public InvalidAccountException(String message){
            super(message);
        }
    }

}
