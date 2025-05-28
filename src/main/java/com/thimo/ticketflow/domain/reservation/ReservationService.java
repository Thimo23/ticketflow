package com.thimo.ticketflow.domain.reservation;

import com.thimo.ticketflow.domain.user.User;
import com.thimo.ticketflow.domain.user.UserRepository;
import com.thimo.ticketflow.infra.errors.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AuthorizationService authorizationService;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              AuthorizationService authorizationService,
                              UserRepository userRepository){
        this.reservationRepository = reservationRepository;
        this.authorizationService = authorizationService;
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    public Page<DtoReservationResponse> getCurrentUserReservationHistory(Pageable pageable){
        User currentUser = authorizationService.getCurrentUser();
        Page<Reservation> reservations = reservationRepository.findByUserId(currentUser.getId(),pageable);
        Page<DtoReservationResponse> reservationPage = reservations.map(DtoReservationResponse::new);
        return reservationPage;
    }
    @Transactional(readOnly = true)
    public Page<DtoReservationResponse> getReservationHistoryForUser(Long id,Pageable pageable){
        User currentUser = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("The user is not found"));
        Page<Reservation> reservations = reservationRepository.findByUserId(currentUser.getId(),pageable);
        Page<DtoReservationResponse> reservationPage = reservations.map(DtoReservationResponse::new);
        return reservationPage;
    }

    @Transactional(readOnly = true)
    public DtoReservationResponse getReservationById(Long id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Reservation not found"));
        return new DtoReservationResponse(reservation);
    }


}
