package org.BusTickets.api.controllers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class SessionsController {
    @PostMapping("/api/sessions")
    void login(){}
    @DeleteMapping("/api/sessions")
    void logout(){}
    @DeleteMapping("/api/accounts")
    void userLeave(){}
    @GetMapping("/api/accounts")
    void getInfoCurrentUser(){}
    @GetMapping("/api/settings")
    void getSettings(){}
}
