package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class AdministratorsMapperDecorator implements AdministratorsMapper{
    @Override
    public AdministratorsDto.Response.Registration entityToRegistrationDto(AdministratorsEntity entity) {
        return AdministratorsDto.Response.Registration.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .position(entity.getPosition())
                .userType(entity.getUserType().name())
                .build();
    }
    @Override
    public AdministratorsDto.Response.Information entityToInformationDto(AdministratorsEntity entity) {
        return AdministratorsDto.Response.Information.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .position(entity.getPosition())
                .userType(entity.getUserType().name())
                .build();
    }
    @Override
    public AdministratorsDto.Response.Editing entityToEditingDto(AdministratorsEntity entity) {
        return AdministratorsDto.Response.Editing.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .position(entity.getPosition())
                .userType(entity.getUserType().name())
                .build();
    }
    @Override
    public AdministratorsEntity registrationDtoToEntity(AdministratorsDto.Request.Registration dto) {
        AdministratorsEntity entity = AdministratorsEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .position(dto.getPosition())
                .build();
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setUserType(Role.admin);
        return entity;
    }
    @Override
    public AdministratorsEntity editingDtoToEntity(AdministratorsDto.Request.Editing dto) {
        //entity.setPassword(dto.getNewPassword());
        return AdministratorsEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .position(dto.getPosition())
                .build();
    }
}
