package org.BusTickets.store.repositories;

import jakarta.transaction.Transactional;
import org.BusTickets.store.entities.JwtTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface JwtTokenRepository extends JpaRepository<JwtTokenEntity,Long> {
    @Query("SELECT jt.valid FROM JwtTokenEntity jt WHERE jt.id = :id and jt.valid = true")
    Boolean findValidById(Long id);

    @Query("SELECT jt.valid FROM JwtTokenEntity jt WHERE jt.token = :token and jt.valid = true")
    Boolean findValidByToken(String token);

    @Query("SELECT jt.valid FROM JwtTokenEntity jt WHERE jt.login = :login and jt.valid = true ")
    Boolean findValidByLogin(String login);

    @Modifying
    @Transactional
    @Query("UPDATE JwtTokenEntity jt SET jt.valid = false WHERE jt.id = :id and jt.valid = true")
    void invalidateById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE JwtTokenEntity jt SET jt.valid = false WHERE jt.token = :token and jt.valid = true")
    void invalidateByToken(String token);
    @Query("SELECT jt.token FROM JwtTokenEntity jt WHERE jt.login = :login and jt.valid = true")
    String findTokenByLogin(String login);


    boolean existsByLoginAndValidTrue(String login);

}
