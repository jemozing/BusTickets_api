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

    public RouteDto.Response.Create createRouteDto(RoutesEntity routesEntity, ScheduleEntity scheduleEntity){
        RouteDto.Response.Create routeDto = RouteDto.Response.Create.builder()
                .fromStation(routesEntity.getFromStation())
                .toStation(routesEntity.getToStation())
                .start(routesEntity.getStart())
                .duration(routesEntity.getDuration())
                .price(routesEntity.getPrice())
                .bus(BusMapper.INSTANCE.entityToInformationDto(routesEntity.getBus()))
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
                .bus(BusMapper.INSTANCE.entityToInformationDto(routesEntity.getBus()))
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
                .bus(BusMapper.INSTANCE.entityToInformationDto(routesEntity.getBus()))
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
    public RoutesEntity mapToRoutesEntity(RouteDto.Request.Create request){
        return RoutesEntity.builder()
                .fromStation(request.getFromStation())
                .toStation(request.getToStation())
                .start(request.getStart())
                .duration(request.getDuration())
                .price(request.getPrice())
                .build();
    }
    public RoutesEntity mapToRoutesEntity(RouteDto.Request.Editing request){
        return RoutesEntity.builder()
                .fromStation(request.getFromStation())
                .toStation(request.getToStation())
                .start(request.getStart())
                .duration(request.getDuration())
                .price(request.getPrice())
                .build();
    }
    public ScheduleEntity mapToScheduleEntity(RouteDto.Request.Create request){
        return ScheduleEntity.builder()
                .fromDate(request.getSchedule().getFromDate())
                .toDate(request.getSchedule().getToDate())
                .period(request.getSchedule().getPeriod())
                .dates(request.getDates().getDates())
                .build();
    }
    public ScheduleEntity mapToScheduleEntity(RouteDto.Request.Editing request){
        return ScheduleEntity.builder()
                .fromDate(request.getSchedule().getFromDate())
                .toDate(request.getSchedule().getToDate())
                .period(request.getSchedule().getPeriod())
                .dates(request.getDates().getDates())
                .build();
    }

}
