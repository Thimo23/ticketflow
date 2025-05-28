package com.thimo.ticketflow.controller;

import com.thimo.ticketflow.domain.user.DtoAuthUser;
import com.thimo.ticketflow.domain.user.DtoRegisterUser;
import com.thimo.ticketflow.domain.user.DtoResponseUser;
import com.thimo.ticketflow.infra.security.AuthenticationService;
import com.thimo.ticketflow.infra.security.DtoJWToken;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    ResponseEntity<DtoResponseUser> register(@RequestBody @Valid DtoRegisterUser dtoRegisterUser,
                                             UriComponentsBuilder uriComponentsBuilder){
        DtoResponseUser newUser = authenticationService.registerNewUser(dtoRegisterUser);
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(newUser.id()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @PostMapping("/login")
    ResponseEntity<DtoJWToken> login(@RequestBody DtoAuthUser dtoAuthUser){
        DtoJWToken dtoJWToken = authenticationService.authenticateUser(dtoAuthUser);
        return ResponseEntity.ok(dtoJWToken);
    }
}
