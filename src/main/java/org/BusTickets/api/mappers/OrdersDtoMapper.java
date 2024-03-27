package org.BusTickets.api.mappers;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.dto.OrdersDto;
import org.BusTickets.api.dto.PassengersDto;
import org.BusTickets.store.entities.OrdersEntity;
import org.BusTickets.store.entities.RoutesEntity;
import org.BusTickets.store.entities.ScheduleEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrdersDtoMapper {

    /**
     * 3.18 Заказ билетов - ответ
     * 3.19 Получение списка заказов - ответ
     *
     * @param ordersEntity
     * @param routesEntity
     * @return
     */
    public OrdersDto.Response.BookingTickets bookingTickets(OrdersEntity ordersEntity, RoutesEntity routesEntity){
        OrdersDto.Response.BookingTickets bookingTicketsDto = OrdersDto.Response.BookingTickets.builder()
                .orderId(ordersEntity.getId())
                .tripId(routesEntity.getId())
                .fromStation(routesEntity.getFromStation())
                .toStation(routesEntity.getToStation())
                .busName(routesEntity.getBus().getBus_name())
                .date(ordersEntity.getDate())
                .start(routesEntity.getStart())
                .duration(routesEntity.getDuration())
                .price(routesEntity.getPrice())
                .passengers((PassengersDto.Passenger[]) ordersEntity.getPassengers().toArray())
                .build();
        //добавить totalPrice и
        return bookingTicketsDto;
    }
}
