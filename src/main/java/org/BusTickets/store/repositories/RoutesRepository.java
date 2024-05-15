package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.RoutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoutesRepository extends JpaRepository<RoutesEntity, Long> {

    @Query("SELECT r FROM Routes r " +
            "WHERE (:fromStation is null OR r.fromStation = :fromStation) " +
            "AND (:toStation is null OR r.toStation = :toStation) " +
            "AND (:busName is null OR r.bus.bus_name = :busName) " +
            "AND (:fromDate is null OR r.schedule.fromDate >= :fromDate) " +
            "AND (:toDate is null OR r.schedule.toDate <= :toDate)")
    List<RoutesEntity> findRoutesByParams(@Param("fromStation") String fromStation,
                                          @Param("toStation") String toStation,
                                          @Param("busName") String busName,
                                          @Param("fromDate") LocalDate fromDate,
                                          @Param("toDate") LocalDate toDate);
    Optional<RoutesEntity> findById(Long routeId);
}
