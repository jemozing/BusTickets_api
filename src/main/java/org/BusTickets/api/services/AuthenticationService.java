package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.api.controllers.AdministratorController;
import org.BusTickets.api.dto.JwtAuthenticationDto;
import org.BusTickets.api.dto.UsersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
    /*public JwtAuthenticationDto.Response.JwtToken signUp(UsersDto.Request.SignIn request) {

        var user = UsersEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationDto.Response.JwtToken.builder()
                .token(jwt)
                .build();;
    }*/
    public void signOut(){}
    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationDto.Response.JwtToken signIn(UsersDto.Request.SignIn request) {
        // Проверка сходства пароля
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(request.getLogin());
        logger.info(userDetails.getUsername() + "  " + userDetails.getPassword());
        logger.info(request.getLogin() + "  " + request.getPassword());
        logger.info(String.valueOf(passwordEncoder.matches(request.getPassword(), userDetails.getPassword())));
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Неверное имя пользователя или пароль");
        }
        logger.info("пароль верный");
        logger.info(authenticationManager.toString());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        logger.info("получили authManager");
        logger.info(authenticationManager.toString());
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getLogin());
        logger.info("нашли пользователя");
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationDto.Response.JwtToken.builder()
                .token(jwt)
                .build();
    }
}