package ru.bootcamp.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.bootcamp.crm.model.User;
import ru.bootcamp.crm.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getByLogin(username);
        if (user == null) throw new UsernameNotFoundException("Пользователь не найден!");
        return user;
    }

    public boolean checkUsername(Authentication authentication, @PathVariable("numberPhone") String numberPhone) {
        return authentication.getName().equals(numberPhone);
    }
}
