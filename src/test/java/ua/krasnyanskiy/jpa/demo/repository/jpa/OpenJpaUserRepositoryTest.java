package ua.krasnyanskiy.jpa.demo.repository.jpa;

import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.krasnyanskiy.jpa.demo.dto.User;
import ua.krasnyanskiy.jpa.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;

public class OpenJpaUserRepositoryTest {

    @Mock
    EntityManager entityManagerMock;

    @Mock
    Query queryMock;

    UserRepository userRepository;
    User dummyUser;
    User anotherDummyUser;

    @BeforeMethod
    public void before() {
        initMocks(this);
        userRepository = new OpenJpaUserRepository(entityManagerMock);

        dummyUser = new User();
        dummyUser.setName("Buddha");
        dummyUser.setEmail("buddha@gmail.com");
        dummyUser.setId(1);

        anotherDummyUser = new User();
        anotherDummyUser.setName("Mark");
        anotherDummyUser.setEmail("mark@gmail.com");
        anotherDummyUser.setId(2);
    }

    @Test
    public void should_return_proper_user_entity_by_id() {

        /** Given **/
        doReturn(dummyUser).when(entityManagerMock).find(User.class, 1L);


        /** When **/
        User user = userRepository.findUserById(1L);


        /** Than **/
        assertNotNull(user);
        assertEquals(user.getEmail(), "buddha@gmail.com");
        verify(entityManagerMock, times(1)).find(User.class, 1L);
        verifyNoMoreInteractions(entityManagerMock);
    }


    @Test
    public void should_return_all_users() {

        /** Given **/
        doReturn(queryMock).when(entityManagerMock).createQuery(anyString());
        doReturn(asList(dummyUser, anotherDummyUser)).when(queryMock).getResultList();


        /** When **/
        Collection<User> users = userRepository.getAllUsers();


        /** Than **/
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertSame(users.size(), 2);

        verify(entityManagerMock, times(1)).createQuery(anyString());
        verify(queryMock, times(1)).getResultList();
        verifyNoMoreInteractions(entityManagerMock, queryMock);
    }


    @AfterMethod
    public void after() {
        reset(entityManagerMock, queryMock);
        userRepository = null;
        dummyUser = null;
        anotherDummyUser = null;
    }
}