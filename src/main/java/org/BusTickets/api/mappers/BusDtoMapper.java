package org.BusTickets.api.mappers;

import lombok.AllArgsConstructor;
import org.BusTickets.api.dto.BusDto;
import org.BusTickets.store.entities.BusEntity;
import org.BusTickets.store.repositories.BusRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BusDtoMapper {
    private final BusRepository busRepository;
    public BusDto.Response.Information makeBusInformationDto(BusEntity entity) {
        return BusDto.Response.Information.builder()
                .busName(entity.getBus_name())
                .busNumber(entity.getBus_number())
                .busRange(entity.getBus_range())
                .numOfSeats(entity.getNum_of_seats())
                .build();
    }

    public BusDto.Response.InfoAboutBusBrands makeBusBrandsDto(BusEntity entity) {
        return BusDto.Response.InfoAboutBusBrands.builder()
                .busNumber(entity.getBus_number())
                .numOfSeats(entity.getNum_of_seats())
                .build();
    }
}
