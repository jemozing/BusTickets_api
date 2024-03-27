package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.entities.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientsDtoMapper {
    public ClientsDto.Response.Registration makeClientsRegistrationDto(ClientsEntity entity) {
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

    public ClientsDto.Response.Information makeClientsInfoDto(ClientsEntity entity) {
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

    public ClientsDto.Response.Editing makeClientsEditingDto(ClientsEntity entity) {
        return ClientsDto.Response.Editing.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .userType(entity.getUserType().name())
                .build();
    }

    public ClientsEntity makeClientUserEntity(ClientsDto.Request.Registration dto) {
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

    public ClientsEntity EditClientEntity(ClientsDto.Request.Editing dto) {
        ClientsEntity entity = ClientsEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
        entity.setPassword(dto.getNewPassword());
        return entity;
    }
}
