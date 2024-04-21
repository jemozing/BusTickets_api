package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.OrdersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/orders")
@Transactional
@RestController
public class OrdersController {
    @GetMapping("")
    List<?> getBookingOrders(@RequestParam("fromStation") String fromStation,
                                                             @RequestParam("toStation") String toStation,
                                                             @RequestParam("busName") String busName,
                                                             @RequestParam("fromDate") String fromDate,
                                                             @RequestParam("toDate") String toDate,
                                                             @RequestParam("clientId") String clientId,
                                                             @Valid HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> bookingOrders(@RequestBody OrdersDto.Request.BookingTickets bookingOrder,
                                    HttpServletRequest request,
                                    HttpServletResponse response){

        return null;
    }
    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> deleteOrder(@PathVariable String orderId,
                                  HttpServletRequest request,
                                  HttpServletResponse response){

        return null;
    }
}
