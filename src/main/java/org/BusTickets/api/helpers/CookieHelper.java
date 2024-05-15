package org.BusTickets.api.helpers;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.BusTickets.api.services.JwtService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CookieHelper {
    private final JwtService jwtService;
    public long getUserIDInCookie(Cookie[] cookies) throws Exception  {
        // Перебираем куки и ищем JAVASESSIONID
        String sessionId = getSessionId(cookies);
        long userId = 0;
        // Если нашли JAVASESSIONID, обрабатываем его через jwtDecoder
        if (sessionId != null) {
           userId = jwtService.extractUserId(sessionId);
        }
        return userId;
    }
    public String getSessionId(Cookie[] cookies){
        String sessionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JAVASESSIONID")) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        return sessionId;
    }
    public Pair<Long,String> getIdAndRole(Cookie[] cookies){
        // Перебираем куки и ищем JAVASESSIONID
        String sessionId = getSessionId(cookies);
        long userId;
        log.info(sessionId);
        // Если нашли JAVASESSIONID, обрабатываем его через jwtDecoder
        if (sessionId != null) {
            userId = jwtService.extractUserId(sessionId);
            log.info("userId" + Long.toString(userId));
            String userRole = jwtService.extractUserRole(sessionId);
            log.info("userRole" + userRole);
            return Pair.of(userId,userRole);
        } else {
        return null;
        }
    }
}
