package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.RouteDto;
import org.BusTickets.api.helpers.CookieHelper;
import org.BusTickets.api.services.TripsService;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/trips")
@Transactional
@RestController
public class TripsController {
    TripsService tripsService;
    CookieHelper cookieHelper;
    @GetMapping("")
    ResponseEntity<?> getListRoute(
            @RequestParam(value = "fromStation", required = false) String fromStation,
            @RequestParam(value = "toStation", required = false) String toStation,
            @RequestParam(value = "busName", required = false) String busName,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @Valid HttpServletRequest request, HttpServletResponse response){
        try {
            Pair<Long, String> curUser = cookieHelper.getIdAndRole(request.getCookies());
            if(curUser.getSecond().equals("client")) {
                return ResponseEntity.ok().body(
                        tripsService.getInformationAboutRoutesForClients(fromStation,toStation,busName,fromDate,toDate));
            } else if(curUser.getSecond().equals("admin")){
                return ResponseEntity.ok().body(
                        tripsService.getInformationAboutRoutesForAdmins(fromStation,toStation,busName,fromDate,toDate));
            }
            else {
                return ResponseEntity.ok().body("Ничего не найдено, или вы подделали роль)");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("По заданным параметрам поездок не найдено"  + e.getMessage());
        }
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> createRoute(@Valid @RequestBody RouteDto.Request.Create newRoute , HttpServletRequest request, HttpServletResponse response){
        try{
            return ResponseEntity.ok().body(tripsService.createRoute(newRoute));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Неправильно указаны данные" + e.getMessage());
        }
    }
    @PutMapping("/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> editingRoute(@PathVariable Long routeId, @Valid @RequestBody RouteDto.Request.Editing editeRoute, HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @DeleteMapping("/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> deleteRoute(@PathVariable Long routeId,@Valid HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok().body(tripsService.deleteRoute(routeId));
    }
    @GetMapping("/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> infoRoute(@PathVariable Long routeId,@Valid HttpServletRequest request, HttpServletResponse response){
        try {
            RouteDto.Response.InformationForAdmins informationForAdmins = tripsService.getInformationAboutRoute(routeId);
            return ResponseEntity.ok().body(informationForAdmins);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Такой маршрут не найден)" + e.getMessage());
        }
    }
    @PutMapping("/{routeId}/approve")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> approveRoute(@PathVariable Long routeId,@Valid HttpServletRequest request, HttpServletResponse response){
        try{
            return ResponseEntity.ok().body(tripsService.approvedRoute(routeId));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Невозможно утвердить рейс" + e.getMessage());
        }
    }
}
