package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.ScheduleDto;
import org.BusTickets.store.entities.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Mapper(componentModel = "spring")
public interface ScheduleDtoMapper {

    ScheduleDtoMapper INSTANCE = Mappers.getMapper(ScheduleDtoMapper.class);

    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    @Mapping(source = "period", target = "period")
    ScheduleEntity DtoToEntity(ScheduleDto.Request.Schedule schedule);

    @Mapping(source = "fromDate", target = "fromDate")
    @Mapping(source = "toDate", target = "toDate")
    @Mapping(source = "period", target = "period")
    ScheduleDto.Request.Schedule EntityToDto(ScheduleEntity entity);
}
