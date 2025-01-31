package com.thimo.ticketflow.domain.seat;

import com.thimo.ticketflow.domain.section.Section;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name = "seat")
@Entity(name = "Seat")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number",nullable = false)
    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "section_id",nullable = false)
    private Section section;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_status",nullable = false)
    private SeatStatus seatStatus;
}
