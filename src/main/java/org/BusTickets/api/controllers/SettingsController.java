package org.BusTickets.api.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.BusTickets.api.dto.SettingsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/settings")
@RestController
public class SettingsController {
    @GetMapping("")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<?> getSettings(@RequestBody SettingsDto settingsDto, @Valid HttpServletRequest request,
                                  @Valid HttpServletResponse response){
        return null;
    }
}
