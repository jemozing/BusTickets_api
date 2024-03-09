package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.dto.AdministratorsDto;
import org.BusTickets.api.dto.JwtAuthenticationDto;
import org.BusTickets.api.dto.UsersDto;
import org.BusTickets.store.entities.UsersEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientService ClientService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    /*public JwtAuthenticationDto.Response.JwtToken signUp(AdministratorsDto.Request.Registration request) {

        var user = UsersEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    *//**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     *//*
    public JwtAuthenticationDto.Response.JwtToken signIn(UsersDto.Request.SignIn request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }*/
}