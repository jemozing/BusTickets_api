package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "places",indexes = {@Index(name = "idx_route_id_date", columnList = "route_id, date")})
public class PlacesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDate date;
    @ElementCollection
    List<String> availablePlaces;
    @ManyToOne
    @JoinColumn(name = "route_id")
    RoutesEntity route;
}
