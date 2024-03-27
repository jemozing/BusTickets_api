package org.BusTickets.api.mappers;

import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.entities.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class AdministratorsDtoMapper {

    public AdministratorsDto.Response.Registration makeAdministratorsRegistrationDto(AdministratorsEntity entity){
        return AdministratorsDto.Response.Registration.builder()
               .Id(entity.getId())
               .firstName(entity.getFirstName())
               .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .position(entity.getPosition())
                .userType(entity.getUserType().name())
                .build();
    }

    public AdministratorsDto.Response.Information makeAdministratorsInfoDto(AdministratorsEntity entity){
        return AdministratorsDto.Response.Information.builder()
                .Id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .position(entity.getPosition())
                .userType(entity.getUserType().name())
                .build();
    }

    public AdministratorsDto.Response.Editing makeAdministratorsEditingDto(AdministratorsEntity entity){
        return AdministratorsDto.Response.Editing.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymic(entity.getPatronymic())
                .position(entity.getPosition())
                .userType(entity.getUserType().name())
                .build();
    }

    public AdministratorsEntity makeAdministratorsUserEntity(AdministratorsDto.Request.Registration dto){
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

    public AdministratorsEntity editAdministratorsEntity(AdministratorsDto.Request.Editing dto) {
        AdministratorsEntity entity = AdministratorsEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .position(dto.getPosition())
                .build();
        entity.setPassword(dto.getNewPassword());
        return entity;
    }
}
