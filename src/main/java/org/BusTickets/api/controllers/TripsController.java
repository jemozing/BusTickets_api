package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.RouteDto;
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
    @GetMapping("")
    List<RouteDto.Response.Information> getListRoute(@Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> createRoute(@Valid @RequestBody RouteDto.Request.Create newRoute , HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PutMapping("/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> editingRoute(@PathVariable String routeId, @Valid @RequestBody RouteDto.Request.Editing editeRoute, HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @DeleteMapping("/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> deleteRoute(@PathVariable String routeId,@Valid HttpServletRequest request, HttpServletResponse response){return null;}
    @GetMapping("/{routeId}")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> infoRoute(@PathVariable String routeId,@Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PutMapping("/{routeId}/approve")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> approveRoute(@PathVariable String routeId,@Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
}
