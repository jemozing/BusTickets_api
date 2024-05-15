package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.PlacesDto;
import org.BusTickets.api.services.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/places")
@Transactional
@RestController
public class PlacesController {
    OrdersService ordersService;
    @PostMapping("/{orderId}")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> getFreePlaces(@PathVariable Long orderId,
                                    @Valid HttpServletRequest request,
                                    HttpServletResponse response){
        try {
            return ResponseEntity.ok().body(ordersService.getAvailableSeatsForRouteAndDate(orderId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Заказ не найден" + e.getMessage());
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> choosePlace(@RequestBody PlacesDto.Request.ChoosePlace newChoosePlace,
                                  @Valid HttpServletRequest request,
                                  HttpServletResponse response){
        try {
            return ResponseEntity.ok().body(ordersService.choiceAvailablePlace(newChoosePlace));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка " + e.getMessage());
        }
    }

}
