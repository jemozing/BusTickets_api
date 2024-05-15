package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.OrdersDto;
import org.BusTickets.api.helpers.CookieHelper;
import org.BusTickets.api.services.OrdersService;
import org.springframework.data.util.Pair;
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
    private final OrdersService ordersService;
    private CookieHelper cookieHelper;
    @GetMapping("")
    ResponseEntity<?> getBookingOrders(@RequestParam("fromStation") String fromStation,
                                                             @RequestParam("toStation") String toStation,
                                                             @RequestParam("busName") String busName,
                                                             @RequestParam("fromDate") String fromDate,
                                                             @RequestParam("toDate") String toDate,
                                                             @RequestParam("clientId") Long clientId,
                                                             @Valid HttpServletRequest request, HttpServletResponse response){
        try {
            Pair<Long, String> curUser = cookieHelper.getIdAndRole(request.getCookies());
            if(curUser.getSecond().equals("client")) {
                return ResponseEntity.ok().body(ordersService.getInformationAboutOrders(
                        fromStation, toStation, busName, fromDate, toDate, curUser.getFirst()
                ));
            }
            if(curUser.getSecond().equals("admin")){
                return ResponseEntity.ok().body(ordersService.getInformationAboutOrders(
                        fromStation, toStation, busName, fromDate, toDate, clientId
                ));
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("badRequest");
        }
        return null;
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> bookingOrders(@RequestBody OrdersDto.Request.BookingTickets bookingOrder,
                                    HttpServletRequest request,
                                    HttpServletResponse response){

        try {
            return ResponseEntity.ok().body(ordersService.createOrder(request,bookingOrder));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("!!!?" + e.getMessage());
        }
    }
    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAuthority('client')")
    ResponseEntity<?> deleteOrder(@PathVariable Long orderId,
                                  HttpServletRequest request,
                                  HttpServletResponse response){

        try {
            return ResponseEntity.ok().body(ordersService.deleteOrder(orderId));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("!!!" + e.getMessage());
        }
    }
}
