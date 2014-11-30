package ua.krasnyanskiy.jpa.demo;

import ua.krasnyanskiy.jpa.demo.dto.User;
import ua.krasnyanskiy.jpa.demo.repository.UserRepository;
import ua.krasnyanskiy.jpa.demo.repository.jpa.OpenJpaUserRepository;

/**
 * @author Alexander Krasnyanskiy
 */
@SuppressWarnings("unchecked")
public class AppRunner {

    public static void main(String[] args) {

        UserRepository userRepository = new OpenJpaUserRepository();

        User user = new User();
        user.setName("Miguel");
        user.setEmail("miguel@gmail.com");


        // fixme: need to find some good solution for user saving
        //userRepository.save(user);
        //System.out.println(userRepository.getAll());

        user = userRepository.findUserById(2L);
        System.out.println(" u = " + user.getEmail());

    }
}
