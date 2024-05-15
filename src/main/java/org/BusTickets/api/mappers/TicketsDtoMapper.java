package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.PlacesDto;
import org.BusTickets.store.entities.TicketsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TicketsDtoMapper {
    TicketsDtoMapper INSTANCE = Mappers.getMapper(TicketsDtoMapper.class);

    @Mapping(source = "firstName",target = "passenger.firstName")
    @Mapping(source = "lastName",target = "passenger.lastName")
    @Mapping(source = "passport",target = "passenger.passport")
    @Mapping(source = "place",target = "bus_place")
    TicketsEntity DtoToEntity(PlacesDto.Request.ChoosePlace places);

    @Mapping(source = "id",target = "ticket")
    @Mapping(source = "passenger.firstName",target = "firstName")
    @Mapping(source = "passenger.lastName",target = "lastName")
    @Mapping(source = "passenger.passport",target = "passport")
    @Mapping(source = "bus_place",target = "place")
    @Mapping(source = "order.id",target = "orderId")
    PlacesDto.Response.ChoosePlace entityToDto(TicketsEntity entity);


}
