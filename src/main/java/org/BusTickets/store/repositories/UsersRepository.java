package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByLogin(String login);

}
