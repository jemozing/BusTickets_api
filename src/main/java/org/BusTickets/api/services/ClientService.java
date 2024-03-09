package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.ClientsRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientsRepository clientsRepository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public ClientsEntity save(ClientsEntity user) {
        return clientsRepository.saveAndFlush(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public ClientsEntity create(ClientsEntity user) {
        if (clientsRepository.existsByLogin(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (clientsRepository.existsByEmail(user.getEmail())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public ClientsEntity getByLogin(String login) {
        return clientsRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService clientDetailsService() {
        return this::getByLogin;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public ClientsEntity getCurrentClient() {
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
    public void setClient() {
        var user = getCurrentClient();
        user.setUserType(Role.user);
        save(user);
    }
}
