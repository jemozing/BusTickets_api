package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.BusDto;
import org.BusTickets.store.entities.BusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BusMapper {

    BusMapper INSTANCE = Mappers.getMapper(BusMapper.class);

    @Mapping(target = "busNumber",source = "bus_number")
    @Mapping(target = "busName",source = "bus_name")
    @Mapping(target = "numOfSeats",source = "num_of_seats")
    @Mapping(target = "busRange",source = "bus_range")
    BusDto.Response.Information entityToInformationDto(BusEntity entity);

    @Mapping(target = "busNumber",source = "bus_number")
    @Mapping(target = "numOfSeats",source = "num_of_seats")
    BusDto.Response.InfoAboutBusBrands entityToInfoAboutBusBrandsDto(BusEntity entity);

}
