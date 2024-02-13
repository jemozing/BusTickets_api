package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Drivers")
@Entity
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String firstName;
    String patronymic;
    @Column(nullable = false)
    int phone;
    @OneToMany
    List<BusEntity> busList;

}
