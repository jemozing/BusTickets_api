package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.PlacesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlacesRepository extends JpaRepository<PlacesEntity, Long> {
    Optional<PlacesEntity> findByRouteIdAndDate(Long routeId, LocalDate date);

}
