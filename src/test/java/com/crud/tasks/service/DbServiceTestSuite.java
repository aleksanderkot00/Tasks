package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @Test
    @Transactional
    public void testSaveAndGetAllTasks() {
        //Given
        int initialNumberOfTasks = dbService.getAllTasks().size();
        Task task1 = new Task("Test task 1", "Test task content 1");
        Task task2 = new Task("Test task 2", "Test task content 2");

        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        int finalNumberOfTasks = dbService.getAllTasks().size();

        //Then
        assertEquals(initialNumberOfTasks + 2, finalNumberOfTasks);
    }

    @Test
    @Transactional
    public void testGetTask() throws TaskNotFoundException {
        //Given
        Task task1 = new Task("Test task 1", "Test task content 1");
        Task task2 = new Task("Test task 2", "Test task content 2");
        dbService.saveTask(task1);
        dbService.saveTask(task2);

        //When
        Task foundTask1 = dbService.getTask(task1.getId());
        Task foundTask2 = dbService.getTask(task2.getId());

        //Then
        assertEquals("Test task 1", foundTask1.getTitle());
        assertEquals("Test task content 1", foundTask1.getContent());
        assertEquals("Test task 2", foundTask2.getTitle());
        assertEquals("Test task content 2", foundTask2.getContent());
    }

    @Test
    @Transactional
    public void testDeleteTask() {
        //Given
        Task task1 = new Task("Test task 1", "Test task content 1");
        Task task2 = new Task("Test task 2", "Test task content 2");
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        int numberOfTasksAfterSaves = dbService.getAllTasks().size();

        //When
        dbService.deleteTask(task1.getId());
        dbService.deleteTask(task2.getId());
        int numberOfTasksAfterDeletes = dbService.getAllTasks().size();

        //Then
        assertEquals(numberOfTasksAfterSaves - 2, numberOfTasksAfterDeletes);
    }
}