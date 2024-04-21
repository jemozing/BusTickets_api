package org.BusTickets.api.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.BusTickets.api.helpers.CookieHelper;
import org.BusTickets.api.mappers.AdministratorsMapper;
import org.BusTickets.api.mappers.ClientsMapper;
import org.BusTickets.store.entities.AdministratorsEntity;
import org.BusTickets.store.entities.ClientsEntity;
import org.BusTickets.store.repositories.UsersRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountService {
    private CookieHelper cookieHelper;
    private UsersRepository usersRepository;
    private AdministratorService administratorService;
    private AdministratorsMapper administratorsMapper;
    private ClientService clientService;
    private ClientsMapper clientsMapper;
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Получаем куки из запроса
        long userId = cookieHelper.getUserIDInCookie(request.getCookies());
        usersRepository.deleteById(userId);
        return "{}";
    }
    public Object getInfoAboutCurrentUser(HttpServletRequest request, HttpServletResponse response) throws Exception{

        if(request.getCookies() !=null) {
            Pair<Long, String> curUser = cookieHelper.getIdAndRole(request.getCookies());
            if (curUser.getFirst() != 0) {
                long userId = curUser.getFirst();
                String userRole = curUser.getSecond();
                if (userRole.equals("admin")) {
                    AdministratorsEntity entity = administratorService.getById(userId);
                    return administratorsMapper.INSTANCE.entityToInformationDto(entity);
                } else if (userRole.equals("client")) {
                    ClientsEntity entity = clientService.getById(userId);
                    return clientsMapper.INSTANCE.entityToInformationDto(entity);
                }
            }
        }
        return "expection";
    }
}
