package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.repositories.AdministratorsRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorsRepository administratorsRepository;

    public AdministratorsEntity save(AdministratorsEntity entity){
        return administratorsRepository.saveAndFlush(entity);
    }

    public AdministratorsEntity create(AdministratorsEntity entity){
        if (administratorsRepository.existsByLogin(entity.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
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
