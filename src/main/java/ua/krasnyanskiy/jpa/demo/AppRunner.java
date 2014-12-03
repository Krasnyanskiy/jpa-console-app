package ua.krasnyanskiy.jpa.demo;

import ua.krasnyanskiy.jpa.demo.dto.User;
import ua.krasnyanskiy.jpa.demo.repository.UserRepository;
import ua.krasnyanskiy.jpa.demo.repository.jpa.OpenJpaUserRepository;

import static java.lang.System.out;

/**
 * @author Alexander Krasnyanskiy
 */
public class AppRunner {

    public static void main(String[] args) {

        UserRepository userRepository = new OpenJpaUserRepository();
        User user = userRepository.findUserById(2L);
        user.setEmail("new.mail@yandex.ru"); // within the same transaction


        out.println("Name: " + user.getName());
        out.println("Email: " + user.getEmail());
        out.println(user.getOrders());


        userRepository.createUser(create());
        userRepository.deleteUserBy(10L);
    }

    static User create (){
        User user = new User();
        user.setName("Sofiko");
        user.setEmail("sofiko@gmail.com");
        return user;
    }
}
