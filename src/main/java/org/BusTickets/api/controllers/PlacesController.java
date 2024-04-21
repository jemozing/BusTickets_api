package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.PlacesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/places")
@Transactional
@RestController
public class PlacesController {

    @PostMapping("/{orderId}")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> getFreePlaces(@PathVariable String orderId,
                                    @Valid HttpServletRequest request,
                                    HttpServletResponse response){
        return null;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> choosePlace(@RequestBody PlacesDto.Request.ChoosePlace newChoosePlace,
                                  @Valid HttpServletRequest request,
                                  HttpServletResponse response){
        return null;
    }

}
