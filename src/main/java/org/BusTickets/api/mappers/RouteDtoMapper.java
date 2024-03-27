package org.BusTickets.api.mappers;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.api.dto.ScheduleDto;
import org.BusTickets.store.entities.RoutesEntity;
import org.BusTickets.store.entities.ScheduleEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RouteDtoMapper {
    private final BusDtoMapper busDtoMapper;

    public RouteDto.Response.Create createRouteDto(RoutesEntity routesEntity, ScheduleEntity scheduleEntity){
        RouteDto.Response.Create routeDto = RouteDto.Response.Create.builder()
                .fromStation(routesEntity.getFromStation())
                .toStation(routesEntity.getToStation())
                .start(routesEntity.getStart())
                .duration(routesEntity.getDuration())
                .price(routesEntity.getPrice())
                .bus(busDtoMapper.makeBusInformationDto(routesEntity.getBus()))
                .status(routesEntity.isStatus())
                .schedule(ScheduleDto.Request.Schedule.builder()
                        .fromDate(scheduleEntity.getFromDate())
                        .toDate(scheduleEntity.getToDate())
                        .period(scheduleEntity.getPeriod())
                        .build())
                .dates(ScheduleDto.Request.Dates.builder()
                        .dates(scheduleEntity.getDates())
                        .build())
                .build();
        return routeDto;
    }
    public RouteDto.Response.Editing EditRouteDto(RoutesEntity routesEntity, ScheduleEntity scheduleEntity){
        RouteDto.Response.Editing routeDto = RouteDto.Response.Editing.builder()
                .fromStation(routesEntity.getFromStation())
                .toStation(routesEntity.getToStation())
                .start(routesEntity.getStart())
                .duration(routesEntity.getDuration())
                .price(routesEntity.getPrice())
                .bus(busDtoMapper.makeBusInformationDto(routesEntity.getBus()))
                .status(routesEntity.isStatus())
                .schedule(ScheduleDto.Request.Schedule.builder()
                        .fromDate(scheduleEntity.getFromDate())
                        .toDate(scheduleEntity.getToDate())
                        .period(scheduleEntity.getPeriod())
                        .build())
                .dates(ScheduleDto.Request.Dates.builder()
                        .dates(scheduleEntity.getDates())
                        .build())
                .build();
        return routeDto;
    }
    public RouteDto.Response.Information infoRouteDto(RoutesEntity routesEntity, ScheduleEntity scheduleEntity){
        RouteDto.Response.Information routeDto = RouteDto.Response.Information.builder()
                .fromStation(routesEntity.getFromStation())
                .toStation(routesEntity.getToStation())
                .start(routesEntity.getStart())
                .duration(routesEntity.getDuration())
                .price(routesEntity.getPrice())
                .bus(busDtoMapper.makeBusInformationDto(routesEntity.getBus()))
                .status(routesEntity.isStatus())
                .schedule(ScheduleDto.Request.Schedule.builder()
                        .fromDate(scheduleEntity.getFromDate())
                        .toDate(scheduleEntity.getToDate())
                        .period(scheduleEntity.getPeriod())
                        .build())
                .dates(ScheduleDto.Request.Dates.builder()
                        .dates(scheduleEntity.getDates())
                        .build())
                .build();
        return routeDto;
    }

}
