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
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Collection;

/**
 * @author Alexander Krasnyanskiy
 * @since 1.0
 */
public abstract class AbstractOpenJpaIntegrationTest extends ExecutionRunner implements Populatable, Configurable, Cleanable {

    protected OpenJpaUserRepository userRepository;
    private EntityManager entityManager;
    private YmlSqlConfigurationAdapter configurationAdapter;

    @BeforeClass
    public void before() {
        entityManager = configure();
        configurationAdapter = attach("testData.yml");
        populate(entityManager, configurationAdapter.getPopulateQueries());
        userRepository = new OpenJpaUserRepository(entityManager);
    }


    @AfterClass
    public void after() {
        clean(entityManager, configurationAdapter.getCleanQueries());
        entityManager.close();
        userRepository = null;
    }


    @Override
    public EntityManager configure() {
        return Persistence.createEntityManagerFactory("testPU").createEntityManager();
    }


    @Override
    public void populate(EntityManager entityManager, Collection<String> queries) {
        execute(entityManager, queries);
    }


    @Override
    public void clean(EntityManager entityManager, Collection<String> queries) {
        execute(entityManager, queries);
    }


    @Override
    public YmlSqlConfigurationAdapter attach(String pathToYmlConfig) {
        Yaml yaml = new Yaml();
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream(pathToYmlConfig);
        return yaml.loadAs(resource, YmlSqlConfigurationAdapter.class);
    }
}
