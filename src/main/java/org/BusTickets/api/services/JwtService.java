package org.BusTickets.api.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.BusTickets.store.entities.JwtTokenEntity;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;
    private final JwtTokenRepository jwtTokenRepository;
    /**
     * Извлечение имени пользователя из токена
     *
     * @param token токен
     * @return имя пользователя
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Извлечение роли пользователя из токена
     *
     * @param token токен
     * @return роль пользователя
     */
    public String extractUserRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
    public Long extractUserId(String token){
        log.info(extractClaim(token,Claims::getId) + "   " + token);
            return Long.parseLong(extractClaim(token,Claims::getId));
    }
    /**
     * Генерация токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    public String generateToken(UserDetails userDetails) {
        if (jwtTokenRepository.existsByLoginAndValidTrue(userDetails.getUsername())) {
            if(jwtTokenRepository.findValidByLogin(userDetails.getUsername())) {
                return jwtTokenRepository.findTokenByLogin(userDetails.getUsername());
            }
        }
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof UsersEntity customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("role", customUserDetails.getUserType());
        }
        String token = generateToken(claims, userDetails);
        jwtTokenRepository.saveAndFlush(JwtTokenEntity.builder()
                .token(token)
                .valid(true)
                .login(userDetails.getUsername())
                .build());
        return token;
    }

    /**
     * Проверка токена на валидность
     *
     * @param token       токен
     * @param userDetails данные пользователя
     * @return true, если токен валиден
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        boolean jwtValid = false;
        if (jwtTokenRepository.existsByLoginAndValidTrue(userDetails.getUsername())) {
            if(jwtTokenRepository.findValidByLogin(userDetails.getUsername())) {
                if(!isTokenExpired(token)){
                    jwtValid = true;
                } else {
                    tokenInvalidate(token);
                }
            }
        }
        return (userName.equals(userDetails.getUsername())) && jwtValid;
    }
    public void tokenInvalidate(String jwtToken){
        jwtTokenRepository.invalidateByToken(jwtToken);
    }
    /**
     * Извлечение данных из токена
     *
     * @param token           токен
     * @param claimsResolvers функция извлечения данных
     * @param <T>             тип данных
     * @return данные
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        log.info(claims.toString());
        return claimsResolvers.apply(claims);
    }

    /**
     * Генерация токена
     *
     * @param extraClaims дополнительные данные
     * @param userDetails данные пользователя
     * @return токен
     */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().id(extraClaims.get("id").toString()).claims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Проверка токена на просроченность
     *
     * @param token токен
     * @return true, если токен просрочен
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Извлечение даты истечения токена
     *
     * @param token токен
     * @return дата истечения
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Извлечение всех данных из токена
     *
     * @param token токен
     * @return данные
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    /**
     * Получение ключа для подписи токена
     *
     * @return ключ
     */
    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
