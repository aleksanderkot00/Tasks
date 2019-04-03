package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("2", "my list", true));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto( "3","my task", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("3", trelloBoardDto.getId());
            assertEquals("my task", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("2", trelloListDto.getId());
                assertEquals("my list", trelloListDto.getName());
                assertEquals(true, trelloListDto.isClosed());
            });
        });

    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("card", "description", "top", "114");
        TrelloCard card = new TrelloCard("card", "description", "top", "114");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("53", "name", "shortUrl");

        when(trelloClient.createNewCard(cardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto createdTrelloCardDtoFromService = trelloService.createTrelloCard(cardDto);

        //Then
        assertEquals("53",createdTrelloCardDtoFromService.getId());
        assertEquals("name",createdTrelloCardDtoFromService.getName());
        assertEquals("shortUrl",createdTrelloCardDtoFromService.getShortUrl());
        verify(emailService, times(1)).send(any());
    }
}