package com.thimo.ticketflow.infra.security;

import com.thimo.ticketflow.domain.user.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

   public DtoResponseUser registerNewUser(DtoRegisterUser dtoRegisterUser){
       String encodedPassword = passwordEncoder.encode(dtoRegisterUser.password());
       User newUser = dtoRegisterUser.toEntity(encodedPassword);
        userRepository.save(newUser);
        return new DtoResponseUser(newUser);
   }

  public DtoJWToken authenticateUser(DtoAuthUser dtoAuthUser){

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoAuthUser.email(),dtoAuthUser.password()));
      User user = (User) userRepository.findByEmail(dtoAuthUser.email()).get();
    String authenticatedUserToken = jwtService.generateToken(user);
    return new DtoJWToken(authenticatedUserToken);
  }
}
