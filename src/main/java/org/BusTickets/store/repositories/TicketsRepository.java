package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.TicketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketsRepository extends JpaRepository<TicketsEntity, Long> {

    Optional<List<TicketsEntity>> findByOrderId(Long orderId);
}
