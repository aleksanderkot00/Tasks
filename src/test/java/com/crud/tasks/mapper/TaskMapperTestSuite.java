package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
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
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(11L, "Test task", "Test task content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(new Long(11),task.getId());
        assertEquals("Test task",task.getTitle());
        assertEquals("Test task content",task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(11L, "Test task", "Test task content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(new Long(11),taskDto.getId());
        assertEquals("Test task",taskDto.getTitle());
        assertEquals("Test task content",taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "Test task 1", "Test task content 1");
        Task task2 = new Task(2L, "Test task 2", "Test task content 2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        //When
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(2, tasksDto.size());
        assertEquals(new Long(1), tasksDto.get(0).getId());
        assertEquals("Test task 1", tasksDto.get(0).getTitle());
        assertEquals("Test task content 1", tasksDto.get(0).getContent());
        assertEquals(new Long(2), tasksDto.get(1).getId());
        assertEquals("Test task 2", tasksDto.get(1).getTitle());
        assertEquals("Test task content 2", tasksDto.get(1).getContent());
    }
}