package ua.krasnyanskiy.jpa.demo.repository;

import ua.krasnyanskiy.jpa.demo.dto.User;

import java.util.Collection;

public interface UserRepository {

    User get(Long id);

    Collection<User> getAll();

    User update(User user);

    void delete(Long id);

    void save(User user);
}
