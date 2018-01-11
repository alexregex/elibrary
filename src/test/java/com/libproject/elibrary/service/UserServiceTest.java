package com.libproject.elibrary.service;

import com.libproject.elibrary.dao.UserDao;
import com.libproject.elibrary.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserById() {
        Integer userId = 1;

        User user = new User();
        user.setId(userId);

        when(userDao.findById(userId)).thenReturn(user);

        User retrievedUser = userService.findById(userId);

        assertThat(retrievedUser, is(equalTo(user)));

        verify(userDao, times(1)).findById(userId);
    }

    @Test
    public void getUserByLogin() {
        String userLogin = "user";

        User user = new User();
        user.setLogin(userLogin);

        when(userDao.findByLogin(userLogin)).thenReturn(user);

        User retrievedUser = userService.findByLogin(userLogin);

        assertThat(retrievedUser, is(equalTo(user)));

        verify(userDao, times(1)).findByLogin(userLogin);
    }

    @Test
    public void getNullIfUserNotExists() {
        Integer userId = 1;

        User retrievedUser = userService.findById(userId);

        assertThat(retrievedUser, is(equalTo(null)));
    }

    @Test
    public void deleteUser() {
        Integer userId = 1;

        User user = new User();

        when(userDao.findById(userId)).thenReturn(user);

        userService.removeUser(user);

        verify(userDao, times(1)).removeUser(user);
    }

    @Test
    public void listOfUser() {
        List<User> users = new ArrayList<>();

        when(userDao.findAllUsers()).thenReturn(users);

        assertThat(users, is(notNullValue()));
    }
}