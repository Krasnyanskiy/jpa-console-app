package ua.krasnyanskiy.jpa.demo.dto.common;

import javax.persistence.EntityManager;

/**
 * @author Alexander Krasnyanskiy
 */
public interface Configurable {

    EntityManager configure();

}
