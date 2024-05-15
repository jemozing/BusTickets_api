package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
    LocalDate date;
    double totalPrice;
    @OneToOne
    RoutesEntity routes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PassengersEntity> passengers;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TicketsEntity> tickets;
    @OneToOne
    ClientsEntity client;
}
