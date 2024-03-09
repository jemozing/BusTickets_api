package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Setter
@Entity
@Table(name = "Clients")
public class ClientsEntity extends UsersEntity{

    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String firstName;
    String patronymic;
    @Column(unique = true,nullable = false)
    String email;
    @Column(nullable = false)
    String phone;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TicketsEntity> tickets;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrdersEntity> orders;

}
