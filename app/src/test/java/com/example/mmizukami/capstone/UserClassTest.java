package com.example.mmizukami.capstone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Ethan on 11/22/2016.
 */
public class UserClassTest {

    private User testUser;

    @Before
    public void setUp() throws Exception {
        testUser = new User(9001, "", "", "", "", "");
        testUser.setUserName("CheeseDeluxe");
        testUser.setRealName("Ethan");
        testUser.setEmail("googolplex1000@gmail.com");
        testUser.setPhone("714-260-7377");
        testUser.setPassword("password");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(9001, testUser.getId());
    }

    @Test
    public void getUserName() throws Exception {
        assertEquals("CheeseDeluxe", testUser.getUserName());
    }

    @Test
    public void getRealName() throws Exception {
        assertEquals("Ethan", testUser.getRealName());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("googolplex1000@gmail.com", testUser.getEmail());
    }

    @Test
    public void getPhone() throws Exception {
        assertEquals("714-260-7377", testUser.getPhone());
    }

    @Test
    public void getPassword() throws Exception {
        assertEquals("password", testUser.getPassword());
    }

}