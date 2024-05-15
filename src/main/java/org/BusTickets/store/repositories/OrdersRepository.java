package org.BusTickets.store.repositories;

import org.BusTickets.store.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

    @Query("SELECT o FROM OrdersEntity o " +
            "WHERE (:fromStation is null OR o.routes.fromStation = :fromStation) " +
            "AND (:toStation is null OR o.routes.toStation = :toStation) " +
            "AND (:busName is null OR o.routes.bus.bus_name = :busName) " +
            "AND (:fromDate is null OR o.date >= :fromDate) " +
            "AND (:toDate is null OR o.date <= :toDate) " +
            "AND (:clientId is null OR o.client.id = :clientId)")
    List<OrdersEntity> findOrdersByParams(@Param("fromStation") String fromStation,
                                          @Param("toStation") String toStation,
                                          @Param("busName") String busName,
                                          @Param("fromDate") Date fromDate,
                                          @Param("toDate") Date toDate,
                                          @Param("clientId") Long clientId);
}