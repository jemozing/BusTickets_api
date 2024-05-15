package org.BusTickets.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.BusTickets.api.helpers.GlobalExceptionHandler;
import org.BusTickets.api.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/accounts")
@RestController
@Slf4j
public class AccountsController {
    GlobalExceptionHandler globalExceptionHandler;
    AccountService accountService;
    @DeleteMapping("")
    ResponseEntity<?> userLeave(@Valid HttpServletRequest request, HttpServletResponse response){
        try{
            return ResponseEntity.ok().body(accountService.deleteUser(request,response));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(globalExceptionHandler.handleException(new Exception("Don't searching account")));
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getInfoCurrentUser(@Valid HttpServletRequest request, HttpServletResponse response){
        try {
            return ResponseEntity.ok().body(accountService.getInfoAboutCurrentUser(request,response));
        } catch (Exception e){
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(globalExceptionHandler.handleException(new Exception("Don't searching account")));
        }
    }
}
