package org.BusTickets.api.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.BusTickets.api.controllers.AdministratorController;
import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.api.mappers.AdministratorsMapper;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.repositories.AdministratorsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.BusTickets.api.helpers.JwtAuthenticationFilter.COOKIE_NAME;

@Service
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorsRepository administratorsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    public AdministratorsEntity save(AdministratorsEntity entity){
        return administratorsRepository.saveAndFlush(entity);
    }

    public AdministratorsEntity create(AdministratorsDto.Request.Registration newAdmin){
        String hashedPassword = passwordEncoder.encode(newAdmin.getPassword());
        AdministratorsEntity entity = AdministratorsMapper.INSTANCE.registrationDtoToEntity(newAdmin);
        entity.setPassword(hashedPassword);
        if (administratorsRepository.existsByLogin(entity.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь "+ entity.getUsername() +" уже существует");
        }
        logger.info(entity.toString());
        return save(entity);
    }
    public AdministratorsEntity edit(AdministratorsDto.Request.Editing editAdmin, HttpServletRequest request){
        var cookies = request.getCookies();
        String jwt = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    jwt = cookie.getValue();
                    logger.info("Прошла авторизация админа");
                    logger.info(jwt);
                    break;
                }
            }
        }

        AdministratorsEntity oldEntity = administratorsRepository.findById(jwtService.extractUserId(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        if(editAdmin.getOldPassword().equals(editAdmin.getNewPassword())){
            logger.info("Старый и новый пароль не может быть одинаковым");
            throw new RuntimeException("Старый и новый пароль не может быть одинаковым");
        }
        if(passwordEncoder.matches(editAdmin.getOldPassword(),oldEntity.getPassword())){
            logger.info("Старый пароль введен неверно");
            throw new RuntimeException("Старый пароль введен неверно");
        }
        AdministratorsEntity newEntity = AdministratorsMapper.INSTANCE.editingDtoToEntity(editAdmin);
        logger.info("Новая сущность:" + newEntity.toString());
        return save(newEntity);
    }
    public AdministratorsEntity getByLogin(String login) {
        return administratorsRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }
    public AdministratorsEntity getById(long id){
        return administratorsRepository.findById(id)
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
