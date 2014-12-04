package ua.krasnyanskiy.jpa.demo.dto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collection;

/**
 * @author Alexander Krasnyanskiy
 * @since 1.0
 */
public abstract class ExecutionRunner {

    /**
     * Execute given queries within same transaction.
     * @param entityManager
     * @param queries
     */
    protected void execute(EntityManager entityManager, Collection<String> queries) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        for (String query : queries) {
            entityManager.createNativeQuery(query).executeUpdate();
        }
        tx.commit();
    }
}
