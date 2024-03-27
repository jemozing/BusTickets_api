package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.OrdersDto;
import org.BusTickets.api.dto.RouteDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class AdminClientController {
    @GetMapping("/api/trips")
    List<RouteDto.Response.Information> getListRoute(@Valid  HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @GetMapping("/api/orders")
    List<OrdersDto.Response.BookingTickets> getBookingOrders(@RequestParam("fromStation") String fromStation, @Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
}
