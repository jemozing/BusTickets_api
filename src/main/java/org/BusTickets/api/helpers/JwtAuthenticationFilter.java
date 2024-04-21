package org.BusTickets.api.helpers;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.BusTickets.api.controllers.AdministratorController;
import org.BusTickets.api.services.JwtService;
import org.BusTickets.api.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String COOKIE_NAME = "JAVASESSIONID"; // Задайте имя вашей куки
    private final JwtService jwtService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Получаем значение куки
        var cookies = request.getCookies();
        logger.info("Получили значение куки");
        String jwt = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    jwt = cookie.getValue();
                    logger.info("Смогли ее обработать и взять значение");
                    logger.info(jwt);
                    break;
                }
            }
        }
        else {
            logger.info("Куки нету, передаем обработку дальше");
            filterChain.doFilter(request, response);
            return;
        }
        if (jwt == null || jwt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Получаем имя пользователя и роль из токена
        var username = jwtService.extractUserName(jwt);
        var role = jwtService.extractUserRole(jwt);
        logger.info(username);
        logger.info(role);
        logger.info("Получили пользователя из куки");
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(role) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService
                    .userDetailsService()
                    .loadUserByUsername(username);
            logger.info("Проверка валидности токена");
            // Если токен валиден, то аутентифицируем пользователя
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                logger.info("Прошли проверку");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        Collections.singleton(new SimpleGrantedAuthority(role)) // Устанавливаем роль пользователя
                );
                logger.info("Установили роль пользователю");
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
                logger.info(authToken.toString());
            }
        }
        logger.info("конец фильтра, если нету авторизации, то проблема еще где-то");
        filterChain.doFilter(request, response);
        logger.info(request.toString());
        logger.info(response.toString());
    }
}

