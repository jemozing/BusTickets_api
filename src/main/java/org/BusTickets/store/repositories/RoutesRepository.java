package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.RoutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutesRepository extends JpaRepository<RoutesEntity, Long> {
}
