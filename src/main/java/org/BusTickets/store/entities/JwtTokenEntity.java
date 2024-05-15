package org.BusTickets.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "jwt_tokens",indexes = {@Index(name = "idx_jwt_token", columnList = "token"),@Index(name = "idx_jwt_login", columnList = "login")})
public class JwtTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String login;
    @Column(length = 250) // Подберите длину в соответствии с длиной вашего JWT токена
    String token;
    boolean valid;
}
