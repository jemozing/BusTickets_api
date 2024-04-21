package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.ScheduleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ScheduleDtoMapper {

    ScheduleDtoMapper INSTANCE = Mappers.getMapper(ScheduleDtoMapper.class);

    @Mapping(target = "dates", expression = "java(generateDates(schedule.getFromDate(), schedule.getToDate(), schedule.getPeriod()))")
    ScheduleDto.Request.Dates scheduleToDates(ScheduleDto.Request.Schedule schedule);

    default List<String> generateDates(String fromDate, String toDate, String daysOfWeek) {
        List<String> dates = new ArrayList<>();

        // Преобразуем даты начала и окончания выполнения маршрута в объекты LocalDate
        LocalDate startDate = LocalDate.parse(fromDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(toDate, DateTimeFormatter.ISO_LOCAL_DATE);

        // Проходим по всем дням в заданном периоде
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            // Проверяем, является ли текущий день недели одним из указанных в периоде
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (daysOfWeek.contains(String.valueOf(dayOfWeek.getValue()))) {
                // Если соответствует, добавляем дату в список
                dates.add(currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
            // Переходим к следующему дню
            currentDate = currentDate.plusDays(1);
        }

        return dates;
    }
}
