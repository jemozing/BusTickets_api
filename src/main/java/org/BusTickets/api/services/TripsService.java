package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.api.helpers.DateCalculatorHelper;
import org.BusTickets.api.mappers.RouteMapper;
import org.BusTickets.store.entities.PlacesEntity;
import org.BusTickets.store.entities.RoutesEntity;
import org.BusTickets.store.repositories.BusRepository;
import org.BusTickets.store.repositories.PlacesRepository;
import org.BusTickets.store.repositories.RoutesRepository;
import org.BusTickets.store.repositories.ScheduleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripsService {
    private final RoutesRepository routesRepository;
    private final DateCalculatorHelper dateCalculatorHelper;
    private final BusRepository busRepository;
    private final ScheduleRepository scheduleRepository;
    private final RouteMapper routeMapper;
    private final PlacesRepository placesRepository;

    public List<RouteDto.Response.InformationForAdmins> getInformationAboutRoutesForAdmins(String fromStation, String toStation, String busName, String fromDate, String toDate){
        LocalDate from = null;
        LocalDate to = null;
        if (fromDate != null && toDate != null) {
            from = LocalDate.parse(fromDate);
            to = LocalDate.parse(toDate);
        }
        List<RoutesEntity> routesEntityList = routesRepository.findRoutesByParams(fromStation,toStation, busName,from,to);
        List<RouteDto.Response.InformationForAdmins> informationList = new ArrayList<>();
        for(RoutesEntity entity : routesEntityList){
            informationList.add(routeMapper.entityToInformationDtoForAdmin(entity));
        }
        return informationList;
    }
    public List<RouteDto.Response.InformationForClients> getInformationAboutRoutesForClients(String fromStation, String toStation, String busName, String fromDate, String toDate){
        LocalDate from = null;
        LocalDate to = null;
        if (fromDate != null && toDate != null) {
            from = LocalDate.parse(fromDate);
            to = LocalDate.parse(toDate);
        }
        List<RoutesEntity> routesEntityList = routesRepository.findRoutesByParams(fromStation,toStation, busName,from,to);
        List<RouteDto.Response.InformationForClients> informationList = new ArrayList<>();
        for(RoutesEntity entity : routesEntityList){
            if(entity.isStatus()){
                informationList.add(routeMapper.entityToInformationDtoForClient(entity));
            }
        }
        return informationList;
    }
    public RouteDto.Response.InformationForAdmins getInformationAboutRoute(Long routeId){
        RoutesEntity entity = routesRepository.findById(routeId).orElseThrow();
        return routeMapper.entityToInformationDtoForAdmin(entity);
    }
    public String approvedRoute(Long routeId){
        try {
            RoutesEntity entity = routesRepository.findById(routeId).orElseThrow();
            entity.setStatus(true);
            entity = routesRepository.saveAndFlush(entity);
            if(generatedPlacesForDatesInRoute(entity).equals("ok"))
                return "ok";
            return "don't generate places";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public String generatedPlacesForDatesInRoute(RoutesEntity entity) throws Exception{
        List<String> avaliablePlaces = new ArrayList<>();
        int numPlaces = entity.getBus().getNum_of_seats();
        for(int i = 1; i <= numPlaces;i++){
            avaliablePlaces.add("место_"+i);
        }
        List<PlacesEntity> placesEntities = new ArrayList<>();

        for(LocalDate localDate : entity.getDates()){
            placesEntities.add(PlacesEntity.builder()
                    .date(localDate)
                    .route(entity)
                    .availablePlaces(avaliablePlaces)
                    .build());
        }
        placesRepository.saveAllAndFlush(placesEntities);
        return "ok";
    }
    public RouteDto.Response.Create createRoute(RouteDto.Request.Create newRoute) throws Exception {
        RoutesEntity routesEntity = routeMapper.requestToRoutesEntity(newRoute);
        if(newRoute.getSchedule() != null && newRoute.getDates().isEmpty()){
            routesEntity.setDates(dateCalculatorHelper.calculateFlightDays(
                    newRoute.getSchedule().getFromDate(),
                    newRoute.getSchedule().getToDate(),
                    newRoute.getSchedule().getPeriod()));
        }
        routesEntity.setBus(busRepository.findById(newRoute.getBusName()).orElseThrow());
        routesEntity.setSchedule(scheduleRepository.saveAndFlush(routesEntity.getSchedule()));

        routesRepository.saveAndFlush(routesEntity);

        return routeMapper.entityToCreateDto(routesEntity);
    }
    public String deleteRoute(Long routeId){
        try {
            routesRepository.deleteById(routeId);
            return "ok";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public void editRoute(){

    }
}
