package com.thimo.ticketflow.infra.security;

import com.thimo.ticketflow.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    public SecurityFilter(JwtService jwtService,UserRepository userRepository){
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      var authHeader = request.getHeader("Authorization");
      if (authHeader != null){
          var token = authHeader.replace("Bearer ","");
          var userEmail = jwtService.getSubject(token);

          if (userEmail != null){
              var user = userRepository.findByEmail(userEmail);
              var authentication = new UsernamePasswordAuthenticationToken(user,null,user.get().getAuthorities());
              SecurityContextHolder.getContext().setAuthentication(authentication);
          }
      }

      filterChain.doFilter(request,response);
    }
}
