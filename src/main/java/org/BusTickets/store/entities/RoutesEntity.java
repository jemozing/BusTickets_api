package org.BusTickets.store.entities;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.query.sqm.IntervalType;

import java.sql.Time;
import java.time.Duration;

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
}
