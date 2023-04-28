package ru.bootcamp.crm.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bootcamp.crm.clients.BrtClient;
import ru.bootcamp.crm.model.User;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> storage = new HashMap<>();
    private BrtClient brtClient;

    @PostConstruct
    public void init() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        List<String> abonentsList = brtClient.getAbonents();

        for (String telNumber : abonentsList) {
            storage.put(telNumber, new User(telNumber, encoder.encode("123"),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
        }

        storage.put("admin", new User("admin", encoder.encode("admin"),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    @Override
    public User getByLogin(String login) {
        return storage.get(login);
    }

    @Override
    public void addUser(String login) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        storage.put(login, new User(login, encoder.encode("123"),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
    }
}
