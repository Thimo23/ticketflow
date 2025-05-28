package com.thimo.ticketflow.infra.errors;

public class EntityIsNotOwnerException extends RuntimeException {
    public EntityIsNotOwnerException(String e){
        super(e);
    }
}
