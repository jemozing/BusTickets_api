package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.AdministratorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorsRepository extends JpaRepository<AdministratorsEntity, Long> {
}
