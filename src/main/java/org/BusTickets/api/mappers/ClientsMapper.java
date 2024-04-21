package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.store.entities.ClientsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientsMapper {

    ClientsMapper INSTANCE = Mappers.getMapper(ClientsMapper.class);

    ClientsDto.Response.Registration entityToRegistrationDto(ClientsEntity entity);

    ClientsDto.Response.Information entityToInformationDto(ClientsEntity entity);

    ClientsDto.Response.Editing entityToEditingDto(ClientsEntity entity);

    ClientsEntity registrationDtoToEntity(ClientsDto.Request.Registration dto);

    ClientsEntity editingDtoToEntity(ClientsDto.Request.Editing dto);
}

