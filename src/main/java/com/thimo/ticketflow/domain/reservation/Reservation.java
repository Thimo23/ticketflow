package com.thimo.ticketflow.domain.reservation;

import com.thimo.ticketflow.domain.seat.Seat;
import com.thimo.ticketflow.domain.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "reservation")
@Entity(name = "Reservation")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "seat_id",nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status",nullable = false)
    private ReservationStatus reservationStatus;

    @Column(name = "reservation_date",nullable = false)
    private Date reservationDate;


}
