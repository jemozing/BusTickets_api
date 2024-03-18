package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.controllers.AdministratorController;
import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.api.mappers.AdministratorsDtoMapper;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.repositories.AdministratorsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorsRepository administratorsRepository;
    private final AdministratorsDtoMapper administratorsDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    public AdministratorsEntity save(AdministratorsEntity entity){
        return administratorsRepository.saveAndFlush(entity);
    }

    public AdministratorsEntity create(AdministratorsDto.Request.Registration newAdmin){
        String hashedPassword = passwordEncoder.encode(newAdmin.getPassword());
        AdministratorsEntity entity = administratorsDtoMapper.makeAdministratorsUserEntity(newAdmin);
        entity.setPassword(hashedPassword);
        if (administratorsRepository.existsByLogin(entity.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь "+ entity.getUsername() +" уже существует");
        }
        logger.info(entity.toString());
        return save(entity);
    }
    public AdministratorsEntity edit(AdministratorsDto.Request.Editing editAdmin){
        AdministratorsEntity entity = administratorsDtoMapper.editAdministratorsEntity(editAdmin);
        if(editAdmin.getOldPassword().equals(editAdmin.getNewPassword())){
            throw new RuntimeException("Старый и новый пароль не может быть одинаковым");
        }
        if(passwordEncoder.matches(editAdmin.getOldPassword(),editAdmin.getNewPassword())){
            throw new RuntimeException("Старый пароль введен неверно");
        }
        logger.info(entity.toString());
        return save(entity);
    }
    public AdministratorsEntity getByLogin(String login) {
        return administratorsRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService adminDetailsService() {
        return this::getByLogin;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public AdministratorsEntity getCurrentAdmin() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void setAdminRole() {
        var admin = getCurrentAdmin();
        admin.setUserType(Role.admin);
        save(admin);
    }
}
