package org.BusTickets.api.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.BusTickets.api.dto.OrdersDto;
import org.BusTickets.api.dto.PassengersDto;
import org.BusTickets.api.dto.PlacesDto;
import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.api.helpers.CookieHelper;
import org.BusTickets.api.mappers.OrdersDtoMapper;
import org.BusTickets.api.mappers.PassengerDtoMapper;
import org.BusTickets.api.mappers.TicketsDtoMapper;
import org.BusTickets.store.entities.*;
import org.BusTickets.store.repositories.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final PlacesRepository placesRepository;
    private final OrdersRepository ordersRepository;
    private final ClientsRepository clientsRepository;
    private final TicketsRepository ticketsRepository;
    private final RoutesRepository routesRepository;
    private final CookieHelper cookieHelper;
    private final OrdersDtoMapper ordersDtoMapper;
    private final TicketsDtoMapper ticketsDtoMapper;
    private final PassengerDtoMapper passengerDtoMapper;
    private final PassengersRepository passengersRepository;

    public PlacesDto.Response.PlacesBus getAvailableSeatsForRouteAndDate(Long orderId){
        OrdersEntity ordersEntity = ordersRepository.getReferenceById(orderId);
        return placesRepository.findByRouteIdAndDate(ordersEntity.getRoutes().getId(), ordersEntity.getDate())
                .map(placesEntity -> PlacesDto.Response.PlacesBus.builder()
                        .placesBus(placesEntity.getAvailablePlaces())
                        .build())
                .orElseGet(() -> PlacesDto.Response.PlacesBus.builder()
                        .placesBus(Collections.emptyList())
                        .build());
    }
    public PlacesDto.Response.ChoosePlace choiceAvailablePlace(PlacesDto.Request.ChoosePlace placeDto){
        OrdersEntity ordersEntity = ordersRepository.findById(placeDto.getOrderId()).orElseThrow();
        PlacesEntity placesEntity = placesRepository.findByRouteIdAndDate(ordersEntity.getRoutes().getId(),ordersEntity.getDate()).orElseThrow();
        placesEntity.getAvailablePlaces().remove("место_" + placeDto.getPlace());
        placesRepository.saveAndFlush(placesEntity);
        String ticket = "Ticket " + ordersEntity.getRoutes().getId() + "_" + placeDto.getPlace();
        PassengersEntity passengersEntity = passengerDtoMapper.DtoToEntity(placeDto);
        TicketsEntity ticketsEntity = new TicketsEntity(
                ticket,
                ordersEntity.getTotalPrice()/ordersEntity.getPassengers().size(),
                ordersEntity.getDate(),
                placeDto.getPlace(),
                passengersEntity,
                ordersEntity.getClient(),
                ordersEntity
                );
        ticketsRepository.saveAndFlush(ticketsEntity);
        return ticketsDtoMapper.entityToDto(ticketsEntity);
    }
    public List<OrdersDto.Response.BookingTickets> getInformationAboutOrders(String fromStation,
                                                                             String toStation,
                                                                             String busName,
                                                                             String fromDate,
                                                                             String toDate,
                                                                             Long clientId){
        List<OrdersEntity> ordersEntityList = ordersRepository.findOrdersByParams(
                fromStation,
                toStation,
                busName,
                Date.from(Instant.parse(fromDate)),
                Date.from(Instant.parse(toDate)),
                clientId);
        List<OrdersDto.Response.BookingTickets> bookingTicketsList = new ArrayList<>();
        for(OrdersEntity entity : ordersEntityList){
            bookingTicketsList.add(ordersDtoMapper.EntityToDto(entity));
        }
        return bookingTicketsList;
    }
    public OrdersDto.Response.BookingTickets createOrder(HttpServletRequest request, OrdersDto.Request.BookingTickets ordersDto)throws Exception{
        RoutesEntity routesEntity = routesRepository.findById(ordersDto.getTripId()).orElseThrow();
        ClientsEntity clientsEntity = clientsRepository.findById(cookieHelper.getUserIDInCookie(request.getCookies())).orElseThrow();
        List<PassengersEntity> passengersEntityList = new ArrayList<>();
        PassengersEntity passengersEntity;
        for(PassengersDto.Passenger passenger : ordersDto.getPassengers()){
            passengersEntity = passengerDtoMapper.DtoToEntity(passenger);
            passengersEntityList.add(passengersRepository.saveAndFlush(passengersEntity));

        }

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setDate(ordersDto.getDate());
        ordersEntity.setTotalPrice(routesEntity.getPrice()*ordersDto.getPassengers().size());
        ordersEntity.setClient(clientsEntity);
        ordersEntity.setRoutes(routesEntity);
        ordersEntity.setPassengers(passengersEntityList);
        ordersRepository.saveAndFlush(ordersEntity);
        return ordersDtoMapper.EntityToDto(ordersEntity);
    }

    public String deleteOrder(Long orderId) {
        try {
            OrdersEntity ordersEntity = ordersRepository.getReferenceById(orderId);
            List<TicketsEntity> ticketsEntities = ticketsRepository.findByOrderId(orderId).orElseThrow();
            PlacesEntity placesEntity = placesRepository.findByRouteIdAndDate(ordersEntity.getRoutes().getId(),ordersEntity.getDate()).orElseThrow();
            for(TicketsEntity ticketsEntity : ticketsEntities){
                placesEntity.getAvailablePlaces().add("место_" + ticketsEntity.getBus_place());
            }
            ticketsRepository.deleteAll(ticketsEntities);
            passengersRepository.deleteAll(ordersEntity.getPassengers());
            ordersRepository.delete(ordersEntity);
            return "ok";
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
