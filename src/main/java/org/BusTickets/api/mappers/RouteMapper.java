package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.store.entities.RoutesEntity;
import org.BusTickets.store.entities.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BusDtoMapper.class)
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    RouteDto.Response.Create entityToCreateDto(RoutesEntity routesEntity, ScheduleEntity scheduleEntity);

    RouteDto.Response.Editing entityToEditingDto(RoutesEntity routesEntity, ScheduleEntity scheduleEntity);

    RouteDto.Response.Information entityToInformationDto(RoutesEntity routesEntity, ScheduleEntity scheduleEntity);

    RoutesEntity requestToRoutesEntity(RouteDto.Request.Create request);

    RoutesEntity requestToRoutesEntity(RouteDto.Request.Editing request);

    ScheduleEntity requestToScheduleEntity(RouteDto.Request.Create request);

    ScheduleEntity requestToScheduleEntity(RouteDto.Request.Editing request);
}

