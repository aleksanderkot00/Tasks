package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test Task 1", "Test Content 1"));
        tasks.add(new Task(2L, "Test Task 2", "Test Content 2"));
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto(1L, "Test Task 1", "Test Content 1"));
        tasksDto.add(new TaskDto(2L, "Test Task 2", "Test Content 2"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test Task 1")))
                .andExpect(jsonPath("$[0].content", is("Test Content 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test Task 2")))
                .andExpect(jsonPath("$[1].content", is("Test Content 2")));

    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test Task 1", "Test Content 1");
        TaskDto taskDto = new TaskDto(1L, "Test Task 1", "Test Content 1");

        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test Task 1")))
                .andExpect(jsonPath("$.content", is("Test Content 1")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Updated Task", "Updated Content");
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated Content");

        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Updated Task")))
                .andExpect(jsonPath("$.content", is("Updated Content")));
    }

    @Test
    public void testSaveTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task", "Content");
        TaskDto taskDto = new TaskDto(1L, "Task", "Content");

        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(dbService, times(1)).saveTask(any(Task.class));
    }
}