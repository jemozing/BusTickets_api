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
    double price;
    Date date;
    int bus_place;
    @OneToOne
    PassengersEntity passenger;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    ClientsEntity client;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    OrdersEntity order;
}
