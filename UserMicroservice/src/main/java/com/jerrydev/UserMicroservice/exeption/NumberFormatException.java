package com.jerrydev.UserMicroservice.exeption;

public class NumberFormatException extends RuntimeException{

    private String message ;
    private String operation;

    public NumberFormatException (String operation, String message, Throwable causa){
        super(String.format("Error durante la operación '%s': %s",operation,message), causa);
        this.message = message ;
        this.operation = operation ;
    }


}
