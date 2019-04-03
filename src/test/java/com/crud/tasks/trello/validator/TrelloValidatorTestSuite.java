package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {

    @Autowired
    private TrelloValidator validator;

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloList trelloList = new TrelloList("1", "list", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);

        TrelloBoard trelloBoard1 = new TrelloBoard("123", "Board 1", lists);
        TrelloBoard trelloBoard2 = new TrelloBoard("411", "test", lists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(trelloBoard1);
        boards.add(trelloBoard2);

        //When
        List<TrelloBoard> validatedBoards = validator.validateTrelloBoards(boards);

        //Then
        assertEquals(1, validatedBoards.size());
    }
}