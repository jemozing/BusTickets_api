package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.PassengersDto;
import org.BusTickets.store.entities.PassengersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassengerDtoMapper {

    PassengerDtoMapper INSTANCE = Mappers.getMapper(PassengerDtoMapper.class);

    PassengersDto.Passenger entityToDto(PassengersEntity entity);
    PassengersEntity DtoToEntity(PassengersDto.Passenger passenger);
}
