package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.PassengersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengersRepository extends JpaRepository<PassengersEntity, Long> {
}
