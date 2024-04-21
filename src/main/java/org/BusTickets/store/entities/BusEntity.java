package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Buses")
@Entity
public class BusEntity {
    @Id
    String bus_number;
    String bus_name;
    int num_of_seats;
    float bus_range;
    @OneToOne
    DriverEntity driver;
}
