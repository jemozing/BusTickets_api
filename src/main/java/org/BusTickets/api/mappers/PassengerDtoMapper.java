package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.PassengersDto;
import org.BusTickets.api.dto.PlacesDto;
import org.BusTickets.store.entities.PassengersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PassengerDtoMapper {

    PassengerDtoMapper INSTANCE = Mappers.getMapper(PassengerDtoMapper.class);

    PassengersDto.Passenger entityToDto(PassengersEntity entity);
    PassengersEntity DtoToEntity(PassengersDto.Passenger passenger);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "passport", target = "passport")
    PassengersEntity DtoToEntity(PlacesDto.Request.ChoosePlace dto);
}
