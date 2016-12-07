package com.example.mmizukami.capstone;

import android.net.Uri;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by mmizukami on 11/22/2016.
 */
public class PetTest {


    private Pet mPet;
    private Uri sampleURI;

    @Before
    public void setUp() throws Exception
    {
        mPet = new Pet();
        mPet.setPetName("TestPetName");
        mPet.setType("TestType");
        mPet.setDescription("TestDescription");
        mPet.setAdaption(true);
        mPet.setLost(true);
        mPet.setImageUri(sampleURI);
    }


    @Test
    public void getId() throws Exception {
    assertEquals(123456789,mPet.getId());
    }

    @Test
    public void getPetName() throws Exception {
    assertEquals("TestPetName",mPet.getPetName());
    }


    @Test
    public void getType() throws Exception {
    assertEquals("TestType",mPet.getType());
    }

    @Test
    public void getDescription() throws Exception {
    assertEquals("TestDescription",mPet.getDescription());
    }

    @Test
    public void isAdaption() throws Exception {
    assertEquals(true,mPet.isAdaption());
    }

    @Test
    public void isLost() throws Exception {
        assertEquals(true,mPet.isLost());
    }


    @Test
    public void getImageUri() throws Exception {
        assertEquals(sampleURI,mPet.getImageUri());
    }



}