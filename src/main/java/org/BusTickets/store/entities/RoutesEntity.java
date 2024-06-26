package org.BusTickets.store.entities;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
    double price;

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
    @ElementCollection
    List<LocalDate> dates;
}
