package com.thimo.ticketflow.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DtoRegisterUser(
        @NotBlank
        String name,
        @NotBlank
        String password,
        @NotBlank
        String email
) {
    public User toEntity(String encodedPassword){
        return new User(null,name,email,encodedPassword,Role.USER);
    }
}
