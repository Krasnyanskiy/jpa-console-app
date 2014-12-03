package ua.krasnyanskiy.jpa.demo.dto.common;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Main interface which loads the date to test database.
 *
 * @author Alexander Krasnyanskiy
 */
public interface Populatable {

    /**
     * Populate database with test data.
     * @param entityManager given EntityManager
     * @param queries queries which contains the simple test data
     */
    void populate(EntityManager entityManager, Collection<String> queries);

    /**
     * Use this method to populate database with YML file data.
     * @param entityManager given EntityManager
     * @param pathToYmlConfig path to YML file
     */
    void populate(EntityManager entityManager, String pathToYmlConfig);
}
