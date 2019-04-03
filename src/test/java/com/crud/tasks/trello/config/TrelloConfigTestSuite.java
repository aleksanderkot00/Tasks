package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void testGetTrelloApiEndpoint() {
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();

        //Then
        assertEquals("https://api.trello.com/1",trelloApiEndpoint);
    }

    @Test
    public void testGetTrelloAppKey() {
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloAppKey();

        //Then
        assertEquals("00946bd1ed9be3879badfb0c751c82cb",trelloApiEndpoint);
    }

    @Test
    public void testGetTrelloToken() {
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloToken();

        //Then
        assertEquals("cfdd2d7e06e35ec197a53dae22558f7c8923a634590c21c5c1c159fdf2fda24d",trelloApiEndpoint);
    }
}