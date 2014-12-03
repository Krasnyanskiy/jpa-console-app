package ua.krasnyanskiy.jpa.demo.dto;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.yaml.snakeyaml.Yaml;
import ua.krasnyanskiy.jpa.demo.dto.common.Cleanable;
import ua.krasnyanskiy.jpa.demo.dto.common.Configurable;
import ua.krasnyanskiy.jpa.demo.dto.common.Populatable;
import ua.krasnyanskiy.jpa.demo.dto.common.YmlSqlConfigurationAdapter;
import ua.krasnyanskiy.jpa.demo.repository.jpa.OpenJpaUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Collection;

/**
 * @author Alexander Krasnyanskiy
 */
public abstract class AbstractOpenJpaIntegrationTest implements Populatable, Configurable, Cleanable {

    protected OpenJpaUserRepository userRepository;
    private EntityManager entityManager;


    @BeforeClass
    public void before() {
        entityManager = configure();
        populate(entityManager, "prepareForTest.yml");
        userRepository = new OpenJpaUserRepository(entityManager);
    }


    @AfterClass
    public void after() {
        clean(entityManager, "prepareForTest.yml");
        entityManager.close();
        userRepository = null;
    }


    @Override
    public EntityManager configure() {
        return Persistence.createEntityManagerFactory("testPU").createEntityManager();
    }


    @Override
    public void populate(EntityManager entityManager, Collection<String> queries) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        for (String query : queries) {
            entityManager.createNativeQuery(query).executeUpdate();
        }
        tx.commit();
    }


    @Override
    public void populate(EntityManager entityManager, String pathToYmlConfig) {
        Yaml yaml = new Yaml();
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream(pathToYmlConfig);
        YmlSqlConfigurationAdapter configurationAdapter = yaml.loadAs(resource, YmlSqlConfigurationAdapter.class);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        for (String query : configurationAdapter.getPopulate()) {
            entityManager.createNativeQuery(query).executeUpdate();
        }
        tx.commit();
    }


    @Override
    public void clean(EntityManager entityManager, Collection<String> queries) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        for (String query : queries) {
            entityManager.createNativeQuery(query).executeUpdate();
        }
        tx.commit();
    }


    @Override
    public void clean(EntityManager entityManager, String pathToYmlConfig) {
        Yaml yaml = new Yaml();
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream(pathToYmlConfig);
        YmlSqlConfigurationAdapter configurationAdapter = yaml.loadAs(resource, YmlSqlConfigurationAdapter.class);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        for (String query : configurationAdapter.getClean()) {
            entityManager.createNativeQuery(query).executeUpdate();
        }
        tx.commit();
    }
}
