package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.id.IncrementGenerator;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Administrators")
public class AdministratorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    String patronymic;
    @Column(nullable = false)
    String position;
    @Column(unique = true,nullable = false)
    String login;
    @Column(nullable = false)
    String password_hash;

}
