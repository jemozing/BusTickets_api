package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class ClientsMapperDecorator implements ClientsMapper{
    @Override
    public ClientsDto.Response.Registration entityToRegistrationDto(ClientsEntity entity) {
        return ClientsDto.Response.Registration.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .userType(entity.getUserType().name())
                .build();
    }

    @Override
    public ClientsDto.Response.Information entityToInformationDto(ClientsEntity entity) {
        return ClientsDto.Response.Information.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .userType(entity.getUserType().name())
                .build();
    }

    @Override
    public ClientsDto.Response.Editing entityToEditingDto(ClientsEntity entity) {
        return ClientsDto.Response.Editing.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .userType(entity.getUserType().name())
                .build();
    }

    @Override
    public ClientsEntity registrationDtoToEntity(ClientsDto.Request.Registration dto) {
        ClientsEntity clientsEntity = ClientsEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
        clientsEntity.setLogin(dto.getLogin());
        clientsEntity.setPassword(dto.getPassword());
        clientsEntity.setUserType(Role.client);
        return clientsEntity;
    }

    @Override
    public ClientsEntity editingDtoToEntity(ClientsDto.Request.Editing dto) {
        //entity.setPassword(dto.getNewPassword());
        return ClientsEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }
}
