package ua.krasnyanskiy.jpa.demo.repository.jpa;

import ua.krasnyanskiy.jpa.demo.dto.User;
import ua.krasnyanskiy.jpa.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public User findUserById(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<User> findUsersByName(String name) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("name"), name));
        return (Collection<User>)(Object) manager.createQuery(criteriaQuery).getResultList();
    }


    @Override
    @SuppressWarnings("unchecked")
    public Collection<User> getAllUsers() {
        return manager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User updateUser(User user) {
        return manager.merge(user);
    }

    @Override
    public void deleteUserBy(Long id) {
        manager.createQuery("delete from User u where u.id =: id");
    }

    @Override
    public void saveUser(User user) {
        manager.persist(user);
    }
}
