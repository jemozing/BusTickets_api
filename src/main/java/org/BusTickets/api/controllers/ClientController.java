package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.dto.OrdersDto;
import org.BusTickets.api.dto.PlacesDto;
import org.BusTickets.api.mappers.ClientsDtoMapper;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.ClientsRepository;
import org.BusTickets.store.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @PostMapping("/api/clients")
    ResponseEntity<?> clientRegistration(@RequestBody ClientsDto.Request.Registration newClient, HttpServletRequest request, HttpServletResponse response){
        try {
            String hashedPassword = passwordEncoder.encode(newClient.getPassword());
            ClientsDto.Request.Registration newClientHash = ClientsDto.Request.Registration.builder()
                    .firstName(newClient.getFirstName())
                    .lastName(newClient.getLastName())
                    .patronymic(newClient.getPatronymic())
                    .email(newClient.getEmail())
                    .phone(newClient.getPhone())
                    .login(newClient.getLogin())
                    .password(hashedPassword)
                    .build();
            logger.info(newClientHash.toString());
            ClientsEntity entity = clientsDtoMapper.makeClientUserEntity(newClientHash);
            logger.info(entity.toString());
            ClientsEntity newEntity = clientsRepository.saveAndFlush(entity);
            logger.info(newEntity.toString());
            ClientsDto.Response.Registration newClientResp = clientsDtoMapper.makeClientsRegistrationDto(newEntity);
            // Генерация нового идентификатора сессии
            request.getSession().getId();
            return ResponseEntity.ok()
                    .body(newClientResp);
        } catch (Exception e){
            logger.error("Все пошло по одному месту");
            logger.error("An error occurred during client registration", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping ("/api/clients")
    ClientsDto.Response.Editing editingClient(){
        return null;
    }
    @PostMapping("/api/orders")
    OrdersDto.Response.BookingTickets bookingOrders(@RequestBody OrdersDto.Request.BookingTickets bookingOrder){
        return null;
    }
    @PostMapping("/api/places/{orderId}")
    int[] getFreePlaces(@PathVariable String orderId){
        return null;
    }
    @PostMapping("/api/places")
    PlacesDto.Response.ChoosePlace choosePlace(@RequestBody PlacesDto.Request.ChoosePlace newChoosePlace){
        return null;
    }
    @DeleteMapping("/api/orders/{orderId}")
    void deleteOrder(@PathVariable String orderId){

    }
}
