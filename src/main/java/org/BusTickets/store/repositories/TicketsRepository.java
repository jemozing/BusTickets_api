package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.TicketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<TicketsEntity, Long> {
}
