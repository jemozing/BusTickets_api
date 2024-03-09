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
        // Проверяем, был ли передан новый пароль
        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            // Проверяем, уникальность нового пароля
            if (isPasswordUnique(dto.getNewPassword())) {

                return AdministratorsEntity.builder()
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .patronymic(dto.getPatronymic())
                        .position(dto.getPosition())
                        .build();
            } else {
                // Вернуть null или бросить исключение в зависимости от вашей логики обработки ошибок
                return null;
            }
        } else {
            // Если новый пароль не был передан, просто обновляем остальные поля
            return AdministratorsEntity.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .patronymic(dto.getPatronymic())
                    .position(dto.getPosition())
                    .build();
        }
    }

    private boolean isPasswordUnique(String newPassword) {
        // Здесь должна быть логика проверки уникальности пароля, например, запрос к базе данных
        // Возвращаем true, если пароль уникален, false - в противном случае
        return true; // Заглушка, замените на вашу реализацию
    }
}
