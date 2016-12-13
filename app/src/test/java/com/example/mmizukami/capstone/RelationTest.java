package com.example.mmizukami.capstone;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by Ethan on 12/12/2016.
 */
 
public class RelationTest {

    private Relation mRelation;
    private Pet petToAdd;
    private User userToAdd;

    @Before
    public void setUp() throws Exception
    {
        petToAdd = new Pet("Bonbon", "Type", "Description", true, true, R.drawable.dog);
        userToAdd = new User(9001, "Cinnamon", "Ethan", "ethan.quach.10@gmail.com", "714-260-7377", "password");
        mRelation = (petToAdd, userToAdd);
    }


    @Test
    public void getId() throws Exception {
    assertEquals(-1, mRelation.getId());
    }

    @Test
    public void getPet() throws Exception {
    assertEquals("Bonbon", mRelation.getPet().getPetName());
    }

    @Test
    public void getUser() throws Exception {
    assertEquals("Cinnamon", mRelation.getUser().getUserName());
    }

}
