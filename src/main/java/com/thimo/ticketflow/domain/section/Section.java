package com.thimo.ticketflow.domain.section;

import com.thimo.ticketflow.domain.event.Event;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "section")
@Entity(name = "Section")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_type",nullable = false)
    private SectionType sectionType;

    @Column(name = "maximum_capacity",nullable = false)
    private Integer maximumCapacity;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
