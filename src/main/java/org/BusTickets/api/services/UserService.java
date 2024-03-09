package org.BusTickets.api.services;

import lombok.RequiredArgsConstructor;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.Role;
import org.BusTickets.store.entities.UsersEntity;
import org.BusTickets.store.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    public UsersEntity getByLogin(String login) {
        return usersRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }
    public UserDetailsService userDetailsService(){
        return  this::getByLogin;
    }
    public UsersEntity getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(username);
    }

    @Deprecated
    public Role getUserRole(){
        var user = getCurrentUser();
        return user.getUserType();
    }
}
