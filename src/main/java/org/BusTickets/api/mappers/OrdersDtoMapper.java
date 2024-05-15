package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.OrdersDto;
import org.BusTickets.store.entities.OrdersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface OrdersDtoMapper {

    OrdersDtoMapper INSTANCE = Mappers.getMapper(OrdersDtoMapper.class);

    @Mapping(source = "id",target = "orderId")
    @Mapping(source = "routes.id",target = "tripId")
    @Mapping(source = "routes.fromStation", target = "fromStation")
    @Mapping(source = "routes.toStation", target = "toStation")
    @Mapping(source = "routes.bus.bus_name", target = "busName")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "routes.start", target = "start")
    @Mapping(source = "routes.duration", target = "duration")
    @Mapping(source = "routes.price", target = "price")
    @Mapping(source = "passengers", target = "passengers")
    @Mapping(source = "totalPrice",target = "totalPrice")
    OrdersDto.Response.BookingTickets EntityToDto(OrdersEntity entity);


}
