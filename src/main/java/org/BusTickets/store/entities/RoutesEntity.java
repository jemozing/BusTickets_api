package org.BusTickets.store.entities;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.PlacesDto;
import org.hibernate.annotations.Type;

import java.sql.Time;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "Routes")
public class RoutesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    boolean status;
    String fromStation;
    String toStation;
    Time start;
    int price;

    @Type(PostgreSQLIntervalType.class)
    @Column(
            name = "duration",
            columnDefinition = "interval"
    )
    Duration duration;

    @OneToOne
    BusEntity bus;
    @OneToOne
    ScheduleEntity schedule;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    Set<PlacesEntity> places;
}
