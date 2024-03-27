package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.dto.PlacesDto;
import org.BusTickets.store.entities.OrdersEntity;
import org.BusTickets.store.entities.PlacesEntity;
import org.BusTickets.store.repositories.PlacesRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientOrdersService {
    private final PlacesRepository placesRepository;




    public PlacesDto.Response.PlacesBus getAvailableSeatsForRouteAndDate(OrdersEntity ordersEntity){
        return placesRepository.findByRouteIdAndDate(ordersEntity.getRoutes().getId(), ordersEntity.getDate())
                .map(placesEntity -> PlacesDto.Response.PlacesBus.builder()
                        .placesBus(placesEntity.getAvailablePlaces())
                        .build())
                .orElseGet(() -> PlacesDto.Response.PlacesBus.builder()
                        .placesBus(Collections.emptyList())
                        .build());
    }
}
