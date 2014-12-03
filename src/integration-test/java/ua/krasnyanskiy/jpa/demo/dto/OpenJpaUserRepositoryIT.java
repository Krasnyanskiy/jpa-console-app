package ua.krasnyanskiy.jpa.demo.dto;

import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;

/**
 * @author Alexander Krasnyanskiy
 */
public class OpenJpaUserRepositoryIT extends AbstractOpenJpaIntegrationUnitTest {

    @Test
    public void should_return_all_user_with_given_name() {
        for (User user : userRepository.findUsersByName("Ahti")) {
            assertNotNull(user);
        }
    }

    @Test
    public void should_return_all_available_users() {
        Collection<User> users = userRepository.getAllUsers();
        assertSame(users.size(), 2);
    }
}