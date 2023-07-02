package ru.netology.springbooth4.service;

import org.springframework.stereotype.Service;
import ru.netology.springbooth4.exception.InvalidCredentials;
import ru.netology.springbooth4.exception.UnauthorizedUser;
import ru.netology.springbooth4.model.Account;
import ru.netology.springbooth4.permissions.Authorities;
import ru.netology.springbooth4.repository.UserRepository;

import java.util.List;
@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

     public List<Authorities> getAuthorities(Account account) {
        if (isEmpty(account.getUser()) || isEmpty(account.getPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(account);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + account.getUser());
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}