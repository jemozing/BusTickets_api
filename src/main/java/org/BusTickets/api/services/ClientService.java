package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.controllers.AdministratorController;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.mappers.ClientsDtoMapper;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.ClientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientsRepository clientsRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientsDtoMapper clientsDtoMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
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
    public ClientsEntity create(ClientsDto.Request.Registration newClient) {
        String hashedPassword = passwordEncoder.encode(newClient.getPassword());
        ClientsEntity entity = clientsDtoMapper.makeClientUserEntity(newClient);
        entity.setPassword(hashedPassword);
        if (clientsRepository.existsByLogin(entity.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (clientsRepository.existsByEmail(entity.getEmail())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        logger.info(entity.toString());
        return save(entity);
    }
    public ClientsEntity edit(ClientsDto.Request.Editing editClient){
        return null;
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
        user.setUserType(Role.client);
        save(user);
    }
}
