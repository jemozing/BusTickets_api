package org.BusTickets.api.controllers;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.api.dto.ClientsDto;
import org.BusTickets.api.factories.AdministratorsDtoFactory;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.repositories.AdministratorsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class DemoConroller {
    AdministratorsDtoFactory administratorsDtoFactory;
    AdministratorsRepository administratorsRepository;
    @GetMapping("/api/admins")
    AdministratorsDto.Response.Registration getAdmins(){
        return new AdministratorsDto.Response.Registration(1L,"nazar","mury","","admin","admin");
    }
    @PostMapping("/api/admin/1")
    AdministratorsDto.Response.Registration getAdmin(@RequestBody AdministratorsDto.Request.Registration newAdmin){
        AdministratorsEntity administratorsEntity = administratorsDtoFactory.makeAdministratorsEntity(newAdmin);
        final AdministratorsEntity saveAdministratorsEntity = administratorsRepository.saveAndFlush(administratorsEntity);
        return administratorsDtoFactory.makeAdministratorsDto(saveAdministratorsEntity);
    }
}
