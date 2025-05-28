package com.thimo.ticketflow.domain.reservation;

import com.thimo.ticketflow.domain.user.Role;
import com.thimo.ticketflow.domain.user.User;
import com.thimo.ticketflow.infra.errors.EntityIsNotOwnerException;
import com.thimo.ticketflow.infra.errors.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AuthorizationService {

    private final ReservationRepository reservationRepository;

    public AuthorizationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        return currentUser;
    }

    public void checkOwnerOrAdmin(Long reservationId) {
        User currentUser = getCurrentUser();
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        assertUserIsOwnerOrAdmin(reservationOptional, currentUser);
    }

    public void assertUserIsOwnerOrAdmin(Optional<Reservation> optionalReservation, User currentUser) {
        Reservation reservation = optionalReservation.orElseThrow(() ->
                new ResourceNotFoundException("Entity not found"));

        if (!reservation.getUser().getId().equals(currentUser.getId()) &&
                !currentUser.getRole().equals(Role.ADMIN)) {
            throw new EntityIsNotOwnerException("The user is not allowed to access this resource");
        }
    }
}
