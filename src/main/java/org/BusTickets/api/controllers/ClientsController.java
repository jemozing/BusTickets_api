package org.BusTickets.api.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.helpers.GlobalExceptionHandler;
import org.BusTickets.api.mappers.AdministratorsMapper;
import org.BusTickets.api.mappers.ClientsMapper;
import org.BusTickets.api.services.ClientService;
import org.BusTickets.api.services.JwtService;
import org.BusTickets.api.services.UserService;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.repositories.ClientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/clients")
@Transactional
@RestController
public class ClientsController {
    ClientsRepository clientsRepository;
    ClientsMapper clientsMapper;
    ClientService clientService;
    private final UserService userService;
    private final JwtService jwtService;
    private final GlobalExceptionHandler globalExceptionHandler;
    private static final Logger logger = LoggerFactory.getLogger(ClientsController.class);
    @PostMapping("")
    ResponseEntity<?> clientRegistration(@RequestBody ClientsDto.Request.Registration newClient, @Valid HttpServletRequest request, HttpServletResponse response){
        try {
            ClientsDto.Response.Registration newClientResp = clientsMapper.entityToRegistrationDto(clientService.create(newClient));
            var user = userService.userDetailsService().loadUserByUsername(newClient.getLogin());
            logger.info(user.toString());
            var jwtToken = jwtService.generateToken(user);
            logger.info(jwtToken);
            // Генерация нового идентификатора сессии
            //request.getSession().getId();
            Cookie cookie = new Cookie("JAVASESSIONID",jwtToken);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // Пример: устанавливает срок действия куки на 24 часа
            cookie.setHttpOnly(true); // Установите true, чтобы предотвратить доступ к куки из JavaScript
            cookie.setSecure(true); // Установите true, если ваше приложение работает по HTTPS
            response.addCookie(cookie);
            return ResponseEntity.ok()
                    .body(newClientResp);
        } catch (Exception e){
            logger.error("Все пошло по одному месту");
            logger.error("An error occurred during client registration", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalExceptionHandler.handleException(e));
        }
    }

    @PutMapping ("")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> editingClient(@RequestBody ClientsDto.Request.Editing editingClient, @Valid HttpServletRequest request, HttpServletResponse response){
        try{
            logger.info("Начинаем редактирование пользователя");
            ClientsDto.Response.Editing clientsResp = clientsMapper
                    .entityToEditingDto(clientService.edit(editingClient, request));
            return ResponseEntity.ok()
                    .body(clientsResp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalExceptionHandler.handleException(e));
        }
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> getInfoClients(@Valid HttpServletRequest request, HttpServletResponse response){
        final List<ClientsEntity> clientsEntitiesList = clientsRepository.findAll();
        ArrayList<ClientsDto.Response.Information> clientsDtoList = new ArrayList<>();
        for (ClientsEntity clientsEntity : clientsEntitiesList) {
            clientsDtoList.add(clientsMapper.entityToInformationDto(clientsEntity));
        }
        return ResponseEntity.ok().body(clientsDtoList);
    }


}
