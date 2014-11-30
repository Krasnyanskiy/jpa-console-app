package ua.krasnyanskiy.jpa.demo.repository;

import ua.krasnyanskiy.jpa.demo.dto.User;

import java.util.Collection;

public interface UserRepository {

    User findUserById(Long id);

    Collection<User> findUsersByName(String name);

    Collection<User> getAllUsers();

    User updateUser(User user);

    void deleteUserBy(Long id);

    void saveUser(User user);
}
