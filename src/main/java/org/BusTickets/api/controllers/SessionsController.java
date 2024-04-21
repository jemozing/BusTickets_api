package org.BusTickets.api.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.JwtAuthenticationDto;
import org.BusTickets.api.dto.UsersDto;
import org.BusTickets.api.helpers.GlobalExceptionHandler;
import org.BusTickets.api.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/sessions")
@RestController
public class SessionsController {
    private final AuthenticationService authenticationService;
    private final GlobalExceptionHandler globalExceptionHandler;

    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    @PostMapping("")
    public ResponseEntity<?> login(@Valid @RequestBody UsersDto.Request.SignIn signInRequest,
                                   @Valid HttpServletRequest request,
                                   @Valid HttpServletResponse response) {
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
    @DeleteMapping("")
    ResponseEntity<?> logout(@Valid HttpServletRequest request,
                             @Valid HttpServletResponse response){
        return null;
    }


}
