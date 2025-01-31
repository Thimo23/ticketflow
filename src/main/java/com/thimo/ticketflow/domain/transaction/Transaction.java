package com.thimo.ticketflow.domain.transaction;

import com.thimo.ticketflow.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "transaction")
@Entity(name = "Transaction")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id",nullable = false)
    private Reservation reservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method",nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "trasaction_status",nullable = false)
    private TransactionStatus transactionStatus;
}
