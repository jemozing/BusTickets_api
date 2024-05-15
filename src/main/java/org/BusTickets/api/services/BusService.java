package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.dto.BusDto;
import org.BusTickets.api.mappers.BusDtoMapper;
import org.BusTickets.api.mappers.BusMapper;
import org.BusTickets.store.entities.BusEntity;
import org.BusTickets.store.entities.PlacesEntity;
import org.BusTickets.store.entities.RoutesEntity;
import org.BusTickets.store.repositories.BusRepository;
import org.BusTickets.store.repositories.PlacesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepository busRepository;
    private final BusMapper busMapper;
    private final PlacesRepository placesRepository;

    public PlacesEntity generatePLacesFullEntity(LocalDate date, RoutesEntity routesEntity){
        List<String> fullPlaces = new ArrayList<>();
        for (int i = 1; i <= routesEntity.getBus().getNum_of_seats(); i++) {
            fullPlaces.add("место_" + i);
        }
        return PlacesEntity.builder()
                .route(routesEntity)
                .date(date)
                .availablePlaces(fullPlaces)
                .build();
    }
    public List<BusDto.Response.InfoAboutBusBrands> getBusBrands(){
        List<BusEntity> busEntityList = busRepository.findAll();
        List<BusDto.Response.InfoAboutBusBrands> busBrandsList = new ArrayList<>();
        for(BusEntity entity : busEntityList){
            busBrandsList.add(busMapper.entityToInfoAboutBusBrandsDto(entity));
        }
        return busBrandsList;
    }
    public BusEntity getBusEntityFromBusBrand(String busBrand){
        return busRepository.findById(busBrand).orElse(new BusEntity());
    }
}
