package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "list", true);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDto);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("123", "Board 1", lists);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("411", "Board 2", lists);
        List<TrelloBoardDto> boardsDto = new ArrayList<>();
        boardsDto.add(trelloBoardDto1);
        boardsDto.add(trelloBoardDto2);

        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardsDto);

        //Then
        assertEquals("123",boards.get(0).getId());
        assertEquals("Board 1",boards.get(0).getName());
        assertEquals("411",boards.get(1).getId());
        assertEquals("Board 2",boards.get(1).getName());
        assertEquals("list",boards.get(1).getLists().get(0).getName());
        assertEquals("1",boards.get(1).getLists().get(0).getId());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "list", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);

        TrelloBoard trelloBoard1 = new TrelloBoard("123", "Board 1", lists);
        TrelloBoard trelloBoard2 = new TrelloBoard("411", "Board 2", lists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(trelloBoard1);
        boards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> boardsDto = trelloMapper.mapToBoardsDto(boards);

        //Then
        assertEquals("123",boards.get(0).getId());
        assertEquals("Board 1",boards.get(0).getName());
        assertEquals("411",boards.get(1).getId());
        assertEquals("Board 2",boards.get(1).getName());
        assertEquals("list",boards.get(1).getLists().get(0).getName());
        assertEquals("1",boards.get(1).getLists().get(0).getId());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list 1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list 2", false);
        List<TrelloListDto> listsDto = new ArrayList<>();
        listsDto.add(trelloListDto1);
        listsDto.add(trelloListDto2);

        //When
        List<TrelloList> lists = trelloMapper.mapToList(listsDto);

        //Then
        assertEquals("1", listsDto.get(0).getId());
        assertEquals("list 1", listsDto.get(0).getName());
        assertEquals("2", listsDto.get(1).getId());
        assertEquals("list 2", listsDto.get(1).getName());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "list 1", true);
        TrelloList trelloList2 = new TrelloList("2", "list 2", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList1);
        lists.add(trelloList2);

        //When
        List<TrelloListDto> listsDto = trelloMapper.mapToListDto(lists);

        //Then
        assertEquals("1", listsDto.get(0).getId());
        assertEquals("list 1", listsDto.get(0).getName());
        assertEquals("2", listsDto.get(1).getId());
        assertEquals("list 2", listsDto.get(1).getName());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("test card", "test card description", "top", "12");

        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);

        //Then
        assertEquals("test card", card.getName());
        assertEquals("test card description", card.getDescription());
        assertEquals("top", card.getPos());
        assertEquals("12", card.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("test card", "test card description", "top", "12");

        //When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);

        //Then
        assertEquals("test card", card.getName());
        assertEquals("test card description", card.getDescription());
        assertEquals("top", card.getPos());
        assertEquals("12", card.getListId());
    }
}