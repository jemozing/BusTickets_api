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
    String id;
    int price;
    Date date;
    int bus_place;
    @OneToOne
    PassengersEntity passenger;
    @OneToOne
    ClientsEntity client;
}
