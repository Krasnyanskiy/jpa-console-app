package ua.krasnyanskiy.jpa.demo.repository.jpa;

import ua.krasnyanskiy.jpa.demo.dto.User;
import ua.krasnyanskiy.jpa.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.Collection;

import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 * @author Alexander Krasnyanskiy
 */
public class OpenJpaUserRepository implements UserRepository {

    private EntityManager manager;

    public OpenJpaUserRepository(EntityManager manager) {
        this.manager = manager;
    }

    public OpenJpaUserRepository() {
        manager = createEntityManagerFactory("simplePU").createEntityManager();
    }

    @Override
    public User get(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<User> getAll() {
        return manager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User update(User user) {
        return manager.merge(user);
    }

    @Override
    public void delete(Long id) {
        manager.createQuery("delete from User u where u.id =: id");
    }

    @Override
    public void save(User user) {
        manager.persist(user);
    }
}
