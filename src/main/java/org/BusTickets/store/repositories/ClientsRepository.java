package org.BusTickets.store.repositories;


import org.BusTickets.store.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {
    Optional<ClientsEntity> findByLogin(String login);
    Optional<ClientsEntity> findById(long id);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
