package org.BusTickets.store.repositories;


import org.BusTickets.store.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {
}
