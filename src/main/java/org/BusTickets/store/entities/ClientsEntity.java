package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Clients")
public class ClientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String firstName;
    String patronymic;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    int phone;
    @Column(unique = true,nullable = false)
    String login;
    String password_hash;

    @OneToMany(cascade = CascadeType.ALL)
    private HashSet<TicketsEntity> tickets;
}
