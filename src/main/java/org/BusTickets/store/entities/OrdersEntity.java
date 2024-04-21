package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date date;
    double totalPrice;
    @OneToOne
    RoutesEntity routes;
    @OneToMany(cascade = CascadeType.ALL)
    private HashSet<PassengersEntity> passengers;
    @OneToMany(cascade = CascadeType.ALL)
    private HashSet<TicketsEntity> tickets;
    @OneToOne
    ClientsEntity client;
}
