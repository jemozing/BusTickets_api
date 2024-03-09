package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.id.IncrementGenerator;
import org.springframework.security.core.userdetails.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Administrators")
public class AdministratorsEntity extends UsersEntity{

    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    String patronymic;
    @Column(nullable = false)
    String position;

}
