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
import org.BusTickets.api.dto.BusDto;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.api.helpers.ErrorsResponses;
import org.BusTickets.api.helpers.GlobalExceptionHandler;
import org.BusTickets.api.mappers.AdministratorsDtoMapper;
import org.BusTickets.api.mappers.ClientsDtoMapper;
import org.BusTickets.api.services.AdminBusService;
import org.BusTickets.api.services.AdministratorService;
import org.BusTickets.api.services.JwtService;
import org.BusTickets.api.services.UserService;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.AdministratorsRepository;
import org.BusTickets.store.repositories.ClientsRepository;
import org.BusTickets.store.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class AdministratorController {
    AdministratorsDtoMapper administratorsDtoMapper;
    ClientsDtoMapper clientsDtoMapper;
    ClientsRepository clientsRepository;
    AdministratorService administratorService;
    AdminBusService adminBusService;
    private final UserService userService;
    private final JwtService jwtService;
    private final GlobalExceptionHandler globalExceptionHandler;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    @PostMapping("/api/admins")
    ResponseEntity<?> registrationAdmin(@RequestBody AdministratorsDto.Request.Registration newAdmin, HttpServletRequest request, HttpServletResponse response){
        try{
            // Хэширование пароля
            AdministratorsDto.Response.Registration newAdminResp = administratorsDtoMapper
                    .makeAdministratorsRegistrationDto(administratorService.create(newAdmin)); //обработка запроса
            var user = userService.userDetailsService().loadUserByUsername(newAdmin.getLogin());
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
                    .body(newAdminResp);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalExceptionHandler.handleException(e));
        }
    }
    @GetMapping("/api/clients")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> getInfoClients(@Valid HttpServletRequest request, HttpServletResponse response){
        final List<ClientsEntity> clientsEntitiesList = clientsRepository.findAll();
        ArrayList<ClientsDto.Response.Information> clientsDtoList = new ArrayList<>();
        for (ClientsEntity clientsEntity : clientsEntitiesList) {
            clientsDtoList.add(clientsDtoMapper.makeClientsInfoDto(clientsEntity));
        }
        return ResponseEntity.ok().body(clientsDtoList);
    }
    @PutMapping("/api/admins")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> editingAdmin(@Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @GetMapping("/api/buses")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> gettingInfoBusBrand(@Valid HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok().body(adminBusService.getBusBrands());
    }
    @PostMapping("/api/trips")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> createRoute(@Valid @RequestBody RouteDto.Request.Create newRoute ,HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PutMapping("/api/trips/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> editingRoute(@PathVariable String routeId, @Valid @RequestBody RouteDto.Request.Editing editeRoute, HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @DeleteMapping("/api/trips/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> deleteRoute(@PathVariable String routeId,@Valid HttpServletRequest request, HttpServletResponse response){return null;}
    @GetMapping("/api/trips/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> infoRoute(@PathVariable String routeId,@Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PutMapping("/api/trips/{routeId}/approve")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> approveRoute(@PathVariable String routeId,@Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
}
