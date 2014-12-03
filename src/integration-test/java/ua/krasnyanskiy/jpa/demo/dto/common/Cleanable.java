package ua.krasnyanskiy.jpa.demo.dto.common;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Alexander Krasnyanskiy
 */
public interface Cleanable {

    void clean(EntityManager entityManager, Collection<String> queries);

    void clean (EntityManager entityManager, String pathToYmlConfig);

}
