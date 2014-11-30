package ua.krasnyanskiy.jpa.demo.dto;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.krasnyanskiy.jpa.demo.repository.jpa.OpenJpaUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static javax.persistence.Persistence.createEntityManagerFactory;
import static org.testng.Assert.assertNotNull;

/**
 * @author Alexander Krasnyanskiy
 */
public class OpenJpaUserRepositoryIT {

    private static final String INSERT_FIRST_USER  = "INSERT INTO user_table (name, email) VALUES ('Aatos', 'aatos@fin.com');";
    private static final String INSERT_SECOND_USER = "INSERT INTO user_table (name, email) VALUES ('Ahti', 'ahti@fin.com')";
    private static final String DELETE_FIRST_USER  = "DELETE FROM user_table u where u.name = 'Aatos'";
    private static final String DELETE_SECOND_USER = "DELETE FROM user_table u where u.name = 'Ahti'";

    private EntityManager manager;
    private OpenJpaUserRepository userRepository;

    @BeforeMethod
    public void before() {
        manager = createEntityManagerFactory("testPU").createEntityManager();
        userRepository = new OpenJpaUserRepository(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
            manager.createNativeQuery(INSERT_FIRST_USER).executeUpdate();
            manager.createNativeQuery(INSERT_SECOND_USER).executeUpdate();
        tx.commit();
    }

    @Test
    public void should_return_all_users_with_given_name() {
        for (User user : userRepository.findUsersByName("Ahti")) {
            assertNotNull(user); // there is only one user with given name (`Ahti`)
        }
    }

    @AfterMethod
    public void after() {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
            manager.createNativeQuery(DELETE_FIRST_USER).executeUpdate();
            manager.createNativeQuery(DELETE_SECOND_USER).executeUpdate();
        tx.commit();
        userRepository = null;
    }
}
