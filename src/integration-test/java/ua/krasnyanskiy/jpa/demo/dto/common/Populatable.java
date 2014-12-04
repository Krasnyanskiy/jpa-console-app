package ua.krasnyanskiy.jpa.demo.dto.common;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Main interface for loading data to the test database.
 *
 * @author Alexander Krasnyanskiy
 * @since 1.0
 */
public interface Populatable {

    /**
     * Populate database with test data.
     * @param entityManager given EntityManager
     * @param queries queries which contains the simple test data
     */
    void populate(EntityManager entityManager, Collection<String> queries);
}
