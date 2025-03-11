package com.thimo.ticketflow.controller;

import com.thimo.ticketflow.domain.reservation.Reservation;
import com.thimo.ticketflow.domain.reservation.ReservationRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final ReservationRepository reservationRepository;

    public UserController(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }
    @GetMapping("/history")    //TODO : IMPLEMENTS THE CORRESPONDING DTO
    public ResponseEntity<List<Reservation>> reservationHistory(){
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok().body(reservations);
    }
}
