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
import org.BusTickets.api.helpers.GlobalExceptionHandler;
import org.BusTickets.api.mappers.AdministratorsDtoMapper;
import org.BusTickets.api.mappers.AdministratorsMapper;
import org.BusTickets.api.mappers.ClientsDtoMapper;
import org.BusTickets.api.services.AdministratorService;
import org.BusTickets.api.services.JwtService;
import org.BusTickets.api.services.UserService;
import org.BusTickets.store.repositories.ClientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/admins")
@Transactional
@RestController
public class AdministratorController {
    AdministratorsDtoMapper administratorsDtoMapper;
    ClientsDtoMapper clientsDtoMapper;
    ClientsRepository clientsRepository;
    AdministratorService administratorService;

    private final UserService userService;
    private final JwtService jwtService;
    private final GlobalExceptionHandler globalExceptionHandler;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    @PostMapping("")
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

    @PutMapping("")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> editingAdmin(@RequestBody AdministratorsDto.Request.Editing newAdmin,@Valid HttpServletRequest request, HttpServletResponse response){
        try{
            logger.info("Начинаем редактирование пользователя");
            AdministratorsDto.Response.Editing newAdminResp = AdministratorsMapper.INSTANCE
                    .entityToEditingDto(administratorService.edit(newAdmin, request));
            return ResponseEntity.ok()
                    .body(newAdminResp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalExceptionHandler.handleException(e));
        }

    }

}
