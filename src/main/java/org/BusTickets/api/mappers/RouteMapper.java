package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.store.entities.RoutesEntity;
import org.BusTickets.store.entities.ScheduleEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring",
        uses = {BusMapper.class, ScheduleDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    @Mapping(target = "routeId", source = "id")
    @Mapping(target = "fromStation", source = "fromStation")
    @Mapping(target = "toStation", source = "toStation")
    @Mapping(target = "start", source = "start")
    @Mapping(target = "duration", source = "duration")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "bus", source = "bus")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "schedule", source = "schedule")
    @Mapping(target = "dates", source = "dates")
    RouteDto.Response.Create entityToCreateDto(RoutesEntity routesEntity);

    RouteDto.Response.Editing entityToEditingDto(RoutesEntity routesEntity);

    @Mapping(target = "routeId", source = "id")
    @Mapping(target = "fromStation", source = "fromStation")
    @Mapping(target = "toStation", source = "toStation")
    @Mapping(target = "start", source = "start")
    @Mapping(target = "duration", source = "duration")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "bus", source = "bus")
    @Mapping(target = "schedule", source = "schedule")
    @Mapping(target = "dates", source = "dates")
    RouteDto.Response.InformationForClients entityToInformationDtoForClient(RoutesEntity routesEntity);
    @Mapping(target = "routeId", source = "id")
    @Mapping(target = "fromStation", source = "fromStation")
    @Mapping(target = "toStation", source = "toStation")
    @Mapping(target = "start", source = "start")
    @Mapping(target = "duration", source = "duration")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "bus", source = "bus")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "schedule", source = "schedule")
    @Mapping(target = "dates", source = "dates")
    RouteDto.Response.InformationForAdmins entityToInformationDtoForAdmin(RoutesEntity routesEntity);
    @Mapping(source = "fromStation", target = "fromStation")
    @Mapping(source = "toStation", target = "toStation")
    @Mapping(source = "start", target = "start")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "busName", target = "bus.bus_name")
    @Mapping(source = "schedule", target = "schedule")
    @Mapping(source = "dates", target = "dates")
    RoutesEntity requestToRoutesEntity(RouteDto.Request.Create request);

    RoutesEntity requestToRoutesEntity(RouteDto.Request.Editing request);

}

