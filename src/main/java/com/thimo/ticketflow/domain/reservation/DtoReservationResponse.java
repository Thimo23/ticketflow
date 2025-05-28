package com.thimo.ticketflow.domain.reservation;

import java.util.Date;

public record DtoReservationResponse(
        Long id,
        ReservationStatus reservationStatus,
        Date reservationDate
) {
    public DtoReservationResponse(Reservation reservation){
        this(reservation.getId(),reservation.getReservationStatus(),reservation.getReservationDate());
    }
}
