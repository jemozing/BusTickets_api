package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministratorsMapper {

    AdministratorsMapper INSTANCE = Mappers.getMapper(AdministratorsMapper.class);

    AdministratorsDto.Response.Registration entityToRegistrationDto(AdministratorsEntity entity);

    AdministratorsDto.Response.Information entityToInformationDto(AdministratorsEntity entity);

    AdministratorsDto.Response.Editing entityToEditingDto(AdministratorsEntity entity);


    AdministratorsEntity registrationDtoToEntity(AdministratorsDto.Request.Registration dto);
    @Mapping(source = "newPassword",target = "password")
    AdministratorsEntity editingDtoToEntity(AdministratorsDto.Request.Editing dto);
}

