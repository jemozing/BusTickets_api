package org.BusTickets.api.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.BusTickets.api.controllers.AdministratorController;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.mappers.AdministratorsMapper;
import org.BusTickets.api.mappers.ClientsMapper;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.repositories.ClientsRepository;
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
public class ClientService {
    private final ClientsRepository clientsRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientsMapper clientsMapper;
    private final JwtService jwtService;
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
        ClientsEntity entity = clientsMapper.registrationDtoToEntity(newClient);
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
    public ClientsEntity edit(ClientsDto.Request.Editing editClient, HttpServletRequest request){
        var cookies = request.getCookies();
        String jwt = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    jwt = cookie.getValue();
                    logger.info("Прошла авторизация клиента");
                    logger.info(jwt);
                    break;
                }
            }
        }
        ClientsEntity oldEntity = clientsRepository.findById(jwtService.extractUserId(jwt))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        if(editClient.getOldPassword().equals(editClient.getNewPassword())){
            logger.info("Старый и новый пароль не может быть одинаковым");
            throw new RuntimeException("Старый и новый пароль не может быть одинаковым");
        }
        if(passwordEncoder.matches(editClient.getOldPassword(),oldEntity.getPassword())){
            logger.info("Старый пароль введен неверно");
            throw new RuntimeException("Старый пароль введен неверно");
        }
        String hashedPassword = passwordEncoder.encode(editClient.getNewPassword());
        oldEntity.setPassword(hashedPassword);
        oldEntity.setFirstName(editClient.getFirstName());
        oldEntity.setLastName(editClient.getLastName());
        oldEntity.setPatronymic(editClient.getPatronymic());
        oldEntity.setEmail(editClient.getEmail());
        oldEntity.setPhone(editClient.getPhone());
        oldEntity.setPassword(hashedPassword);
        logger.info("Новая сущность:" + oldEntity.toString());
        return save(oldEntity);
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
    public ClientsEntity getById(long id){
        return clientsRepository.findById(id)
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
