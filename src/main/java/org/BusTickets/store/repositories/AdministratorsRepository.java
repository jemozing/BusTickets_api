package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorsRepository extends JpaRepository<AdministratorsEntity, Long> {
    Optional<AdministratorsEntity> findByLogin(String login);
    boolean existsByLogin(String login);
}
