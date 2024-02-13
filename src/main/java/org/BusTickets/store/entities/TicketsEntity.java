package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Tickets")
@Entity
public class TicketsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    int price;
    Date date;
    int bus_place;
    @OneToOne
    RoutesEntity route;
    @OneToOne
    PassengersEntity passenger;
}
