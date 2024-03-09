package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.BusDto;
import org.BusTickets.store.entities.BusEntity;
import org.springframework.stereotype.Component;

@Component
public class BusDtoMapper {

    public BusDto.Response.Information makeBusInformationDto(BusEntity entity) {
        return new BusDto.Response.Information(entity.getBus_brand(), entity.getBus_name(), entity.getNum_of_seats(), entity.getBus_range());
    }

    public BusDto.Response.InfoAboutBusBrands makeBusBrandsDto(BusEntity entity) {
        return new BusDto.Response.InfoAboutBusBrands(entity.getBus_brand());
    }
}
