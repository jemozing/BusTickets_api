package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.store.entities.ClientsEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
@DecoratedWith(ClientsMapperDecorator.class)
public interface ClientsMapper {

    ClientsDto.Response.Registration entityToRegistrationDto(ClientsEntity entity);

    ClientsDto.Response.Information entityToInformationDto(ClientsEntity entity);

    ClientsDto.Response.Editing entityToEditingDto(ClientsEntity entity);

    ClientsEntity registrationDtoToEntity(ClientsDto.Request.Registration dto);

    ClientsEntity editingDtoToEntity(ClientsDto.Request.Editing dto);
}

