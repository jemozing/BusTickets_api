package org.BusTickets.api.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.dto.OrdersDto;
import org.BusTickets.api.dto.PlacesDto;
import org.BusTickets.api.helpers.GlobalExceptionHandler;
import org.BusTickets.api.mappers.ClientsDtoMapper;
import org.BusTickets.api.services.ClientService;
import org.BusTickets.api.services.JwtService;
import org.BusTickets.api.services.UserService;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.ClientsRepository;
import org.BusTickets.store.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ClientController {
    ClientsRepository clientsRepository;
    ClientsDtoMapper clientsDtoMapper;
    ClientService clientService;
    PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final GlobalExceptionHandler globalExceptionHandler;
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @PostMapping("/api/clients")
    ResponseEntity<?> clientRegistration(@RequestBody ClientsDto.Request.Registration newClient, HttpServletRequest request, HttpServletResponse response){
        try {
            ClientsDto.Response.Registration newClientResp = clientsDtoMapper
                    .makeClientsRegistrationDto(clientService.create(newClient));
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
    @PutMapping ("/api/clients")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> editingClient(){
        return null;
    }
    @PostMapping("/api/orders")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> bookingOrders(@RequestBody OrdersDto.Request.BookingTickets bookingOrder, HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PostMapping("/api/places/{orderId}")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> getFreePlaces(@PathVariable String orderId, HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PostMapping("/api/places")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> choosePlace(@RequestBody PlacesDto.Request.ChoosePlace newChoosePlace, HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @DeleteMapping("/api/orders/{orderId}")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> deleteOrder(@PathVariable String orderId, HttpServletRequest request, HttpServletResponse response){
        return null;
    }
}
