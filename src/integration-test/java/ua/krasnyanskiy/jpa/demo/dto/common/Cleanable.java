package ua.krasnyanskiy.jpa.demo.dto.common;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * @author Alexander Krasnyanskiy
 * @since 1.0
 */
public interface Cleanable {

    void clean(EntityManager entityManager, Collection<String> queries);

}
