package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<BusEntity, String> {
}
