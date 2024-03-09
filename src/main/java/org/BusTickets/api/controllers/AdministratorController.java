package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.api.dto.BusDto;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.api.mappers.AdministratorsDtoMapper;
import org.BusTickets.api.mappers.ClientsDtoMapper;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.AdministratorsRepository;
import org.BusTickets.store.repositories.ClientsRepository;
import org.BusTickets.store.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    AdministratorsRepository administratorsRepository;
    ClientsDtoMapper clientsDtoMapper;
    ClientsRepository clientsRepository;
    PasswordEncoder passwordEncoder; // Добавлен бин для хэширования паролей
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    @PostMapping("/api/admins")
    ResponseEntity<?> registrationAdmin(@RequestBody AdministratorsDto.Request.Registration newAdmin, HttpServletRequest request, HttpServletResponse response){
        try{
            // Хэширование пароля
            String hashedPassword = passwordEncoder.encode(newAdmin.getPassword());
            AdministratorsDto.Request.Registration newAdminHash = AdministratorsDto.Request.Registration.builder()
                    .firstName(newAdmin.getFirstName())
                    .lastName(newAdmin.getLastName())
                    .patronymic(newAdmin.getPatronymic())
                    .position(newAdmin.getPosition())
                    .login(newAdmin.getLogin())
                    .password(hashedPassword)
                    .build();
            logger.info(newAdminHash.toString());
            AdministratorsEntity administratorsEntity = administratorsDtoMapper.makeAdministratorsUserEntity(newAdminHash);
            logger.info(administratorsEntity.toString());
            AdministratorsEntity newAdminEntity = administratorsRepository.saveAndFlush(administratorsEntity);
            logger.info(newAdminEntity.toString());
            AdministratorsDto.Response.Registration newAdminResp = administratorsDtoMapper.makeAdministratorsRegistrationDto(newAdminEntity);
            // Генерация нового идентификатора сессии
            request.getSession().getId();
            return ResponseEntity.ok()
                    .body(newAdminResp);
        } catch (Exception e){
            logger.error("Все пошло по одному месту");
            logger.error("An error occurred during client registration", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/api/clients")
    List<ClientsDto.Response.Information> getInfoClients(){
        final List<ClientsEntity> clientsEntitiesList = clientsRepository.findAll();
        ArrayList<ClientsDto.Response.Information> clientsDtoList = new ArrayList<>();
        Iterator<ClientsEntity> clientsEntityIterator = clientsEntitiesList.iterator();
        while(clientsEntityIterator.hasNext()){
            clientsDtoList.add(clientsDtoMapper.makeClientsInfoDto(clientsEntityIterator.next()));
        }
        return clientsDtoList;
    }
    @PutMapping("/api/admins")
    AdministratorsDto.Response.Editing editingAdmin(){
        return null;
    }
    @GetMapping("/api/buses")
    List<BusDto.Response.InfoAboutBusBrands> gettingInfoBusBrand(){
        return null;
    }
    @PostMapping("/api/trips")
    RouteDto.Response.Create createRoute(@RequestBody RouteDto.Request.Create newRoute){
        return null;
    }
    @PutMapping("/api/trips/{routeId}")
    RouteDto.Response.Editing editingRoute(@RequestBody RouteDto.Request.Editing editeRoute, @PathVariable String routeId){
        return null;
    }
    @DeleteMapping("/api/trips/{routeId}")
    void deleteRoute(@PathVariable String routeId){}
    @GetMapping("/api/trips/{routeId}")
    RouteDto.Response.Information infoRoute(@PathVariable String routeId){
        return null;
    }
    @PutMapping("/api/trips/{routeId}/approve")
    RouteDto.Response.Information approveRoute(@PathVariable String routeId){
        return null;
    }
}
