package org.BusTickets.store.repositories;


import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {
    Optional<ClientsEntity> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
