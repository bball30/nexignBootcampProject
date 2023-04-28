package ru.bootcamp.crm.repositories;

import ru.bootcamp.crm.model.User;

public interface UserRepository {
    User getByLogin(String login);
    void addUser(String login);
}
