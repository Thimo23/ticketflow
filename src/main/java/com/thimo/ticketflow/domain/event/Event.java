package com.thimo.ticketflow.domain.event;

import com.thimo.ticketflow.domain.section.Section;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "event")
@Entity(name = "Event")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer maximumCapacity;

}
