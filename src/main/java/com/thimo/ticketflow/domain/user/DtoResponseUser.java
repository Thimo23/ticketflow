package com.thimo.ticketflow.domain.user;

public record DtoResponseUser(
        Long id,
        String name,
        String email
) {
    public DtoResponseUser(User user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
