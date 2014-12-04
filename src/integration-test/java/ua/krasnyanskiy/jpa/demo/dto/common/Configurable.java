package ua.krasnyanskiy.jpa.demo.dto.common;

import javax.persistence.EntityManager;

/**
 * @author Alexander Krasnyanskiy
 * @since 1.0
 */
public interface Configurable {

    /**
     * Make EntityManager from EntityManagerFactory.
     * @return configured EntityManager
     */
    EntityManager configure();

    /**
     * Use this method to convert YML file data into adapter.
     * @param pathToYmlConfig path to YML file
     * @return configuration adapter with converted data
     */
    YmlSqlConfigurationAdapter attach(String pathToYmlConfig);

}
