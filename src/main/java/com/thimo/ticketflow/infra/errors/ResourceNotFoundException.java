package com.thimo.ticketflow.infra.errors;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String e){
        super(e);
    }
}
