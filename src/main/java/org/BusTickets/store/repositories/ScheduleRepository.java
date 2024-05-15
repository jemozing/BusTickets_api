package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    @Override
    Optional<ScheduleEntity> findById(Long aLong);
}
