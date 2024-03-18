package org.BusTickets.api.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.JwtAuthenticationDto;
import org.BusTickets.api.dto.UsersDto;
import org.BusTickets.api.helpers.GlobalExceptionHandler;
import org.BusTickets.api.mappers.AdministratorsDtoMapper;
import org.BusTickets.api.mappers.ClientsDtoMapper;
import org.BusTickets.api.services.AdministratorService;
import org.BusTickets.api.services.AuthenticationService;
import org.BusTickets.api.services.ClientService;
import org.BusTickets.api.services.JwtService;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.ClientsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class SessionsController {
    private final AuthenticationService authenticationService;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final JwtService jwtService;
    private final AdministratorService administratorService;
    private final ClientService clientService;
    private final AdministratorsDtoMapper administratorsDtoMapper;
    private final ClientsDtoMapper clientsDtoMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    @PostMapping("/api/sessions")
    public ResponseEntity<?> login(@Valid @RequestBody UsersDto.Request.SignIn signInRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            JwtAuthenticationDto.Response.JwtToken jwtToken = authenticationService.signIn(signInRequest);
            Cookie cookie = new Cookie("JAVASESSIONID",jwtToken.getToken());
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // Пример: устанавливает срок действия куки на 24 часа
            cookie.setHttpOnly(true); // Установите true, чтобы предотвратить доступ к куки из JavaScript
            cookie.setSecure(true); // Установите true, если ваше приложение работает по HTTPS
            response.addCookie(cookie);
            return ResponseEntity.ok(jwtToken);
        } catch (BadCredentialsException e) {
            logger.info(e.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(globalExceptionHandler.handleException(new Exception("Invalid login or password")));
        }
    }
    @DeleteMapping("/api/sessions")
    void logout(){}
    @DeleteMapping("/api/accounts")
    void userLeave(){}
    @GetMapping("/api/accounts")
    public ResponseEntity<?> getInfoCurrentUser(@Valid HttpServletRequest request, HttpServletResponse response){
        try {
            // Получаем куки из запроса
            Cookie[] cookies = request.getCookies();
            // Перебираем куки и ищем JAVASESSIONID
            String sessionId = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JAVASESSIONID")) {
                        sessionId = cookie.getValue();
                        break;
                    }
                }
            }
            // Если нашли JAVASESSIONID, обрабатываем его через jwtDecoder
            if (sessionId != null) {
                String username = jwtService.extractUserName(sessionId);
                String userRole = jwtService.extractUserRole(sessionId);
                if (userRole.equals("admin")){
                    AdministratorsEntity entity = administratorService.getByLogin(username);
                    return ResponseEntity.ok()
                            .body(administratorsDtoMapper.makeAdministratorsInfoDto(entity));
                } else if (userRole.equals("client")) {
                    ClientsEntity entity = clientService.getByLogin(username);
                    return ResponseEntity.ok()
                            .body(clientsDtoMapper.makeClientsInfoDto(entity));
                }
                else return ResponseEntity.badRequest().body("role is not correct");
            }
            return ResponseEntity.badRequest().body("cookie is not correct");
        } catch ( BadCredentialsException e){
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(globalExceptionHandler.handleException(new Exception("Don't searching account")));
        }
    }
    @GetMapping("/api/settings")
    @PreAuthorize("hasAuthority('admin')")
    void getSettings(){}
}
