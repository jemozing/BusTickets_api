package org.BusTickets.api.factories;

import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.springframework.stereotype.Component;

@Component
public class AdministratorsDtoFactory {
    public AdministratorsDto.Response.Registration makeAdministratorsDto(AdministratorsEntity entity){
        return new AdministratorsDto.Response.Registration(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getPatronymic(), entity.getPosition(), "admin");
    }
    public AdministratorsEntity makeAdministratorsEntity(AdministratorsDto.Request.Registration dto){
        return AdministratorsEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .position(dto.getPosition())
                .login(dto.getLogin())
                .password_hash(dto.getPassword())
                .build();
    }
}
