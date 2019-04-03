package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminConfigTestSuite {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void testGetAdminMail() {
        //Given
        //When
        String adminMail = adminConfig.getAdminMail();

        //Then
        assertEquals("kot-aleksander@wp.pl",adminMail);
    }
}