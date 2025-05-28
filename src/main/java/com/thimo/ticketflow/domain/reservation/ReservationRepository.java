package com.thimo.ticketflow.domain.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Page<Reservation> findByUserId(Long userId, Pageable pageable);

    Optional<Reservation> findById(Long reservationId);
}
