package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Schedule")
@Entity
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    Date fromDate;
    Date toDate;
    String period;
    int[] dates;
}

