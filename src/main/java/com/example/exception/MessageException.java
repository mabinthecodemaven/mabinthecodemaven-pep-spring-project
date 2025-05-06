package com.example.exception;

public class MessageException {
    public static class InvalidMessageException extends RuntimeException{
        public InvalidMessageException(String message){
            super(message);
        }
    }

    public static class InvalidUserException extends RuntimeException{
        public InvalidUserException(){
            super("Invalid user");
        }
    }
    
}
