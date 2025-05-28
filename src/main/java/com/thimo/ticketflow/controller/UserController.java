package com.thimo.ticketflow.controller;

import com.thimo.ticketflow.domain.reservation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final ReservationService reservationService;

    private final AuthorizationService authorizationService;

    public UserController(ReservationService reservationService,
                          AuthorizationService authorizationService){
        this.reservationService = reservationService;
        this.authorizationService = authorizationService;
    }
    @GetMapping("/history")
    public ResponseEntity<Page<DtoReservationResponse>> getOwnReservationHistory(Pageable pageable){
        Page<DtoReservationResponse> reservationPage = reservationService.getCurrentUserReservationHistory(pageable);
        return ResponseEntity.ok().body(reservationPage);
    }

    @GetMapping("/history/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<DtoReservationResponse>> getUserReservationHistory(@PathVariable Long id,Pageable pageable){
        Page<DtoReservationResponse> reservationPage = reservationService.getReservationHistoryForUser(id,pageable);
        return ResponseEntity.ok().body(reservationPage);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<DtoReservationResponse> getReservation(@PathVariable Long id){
      authorizationService.checkOwnerOrAdmin(id);
      return ResponseEntity.ok(reservationService.getReservationById(id));
    }
}
