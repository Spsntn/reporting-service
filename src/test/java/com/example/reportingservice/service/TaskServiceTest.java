package com.example.reportingservice.service;

import com.example.reportingservice.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private RestTemplate restTemplate;

    private static final String TASK_SERVICE_URL = "https://hedgehog-open-dolphin.ngrok-free.app/api/tasks";
    private static final String mockToken = "mock-token";

    @Test
    void testGetAllTasks_Success() {
        Task mockTask1 = new Task();
        mockTask1.setId(UUID.randomUUID());
        mockTask1.setName("Task 1");

        Task mockTask2 = new Task();
        mockTask2.setId(UUID.randomUUID());
        mockTask2.setName("Task 2");

        Task[] mockTasksArray = {mockTask1, mockTask2};
        List<Task> mockTasks = Arrays.asList(mockTasksArray);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task[]> mockResponse = new ResponseEntity<>(mockTasksArray, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(TASK_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Task[].class)))
                .thenReturn(mockResponse);

        List<Task> result = taskService.getAlltasks(mockToken);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getName());
        assertEquals("Task 2", result.get(1).getName());
    }

    @Test
    void testGetAllTasks_Unauthorized() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(TASK_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Task[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> taskService.getAlltasks(mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testGetTaskById_Success() {
        UUID taskId = UUID.randomUUID();
        Task mockTask = new Task();
        mockTask.setId(taskId);
        mockTask.setName("Mock Task");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task> mockResponse = new ResponseEntity<>(mockTask, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(TASK_SERVICE_URL + "/" + taskId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Task.class)))
                .thenReturn(mockResponse);

        Task result = taskService.getTaskById(taskId, mockToken);

        assertNotNull(result);
        assertEquals(taskId, result.getId());
        assertEquals("Mock Task", result.getName());
    }

    @Test
    void testGetTaskById_Unauthorized() {
        UUID taskId = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(TASK_SERVICE_URL + "/" + taskId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Task.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> taskService.getTaskById(taskId, mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testGetTasksByProjectId_Success() {
        UUID projectId = UUID.randomUUID();
        Task mockTask1 = new Task();
        mockTask1.setId(UUID.randomUUID());
        mockTask1.setName("Task 1");

        Task mockTask2 = new Task();
        mockTask2.setId(UUID.randomUUID());
        mockTask2.setName("Task 2");

        List<Task> mockTasks = Arrays.asList(mockTask1, mockTask2);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Task>> mockResponse = new ResponseEntity<>(mockTasks, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(TASK_SERVICE_URL + "/by-project/" + projectId),
                eq(HttpMethod.GET),
                eq(entity),
                any(ParameterizedTypeReference.class)))
                .thenReturn(mockResponse);

        List<Task> result = taskService.getTasksByProjectId(projectId, mockToken);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getName());
        assertEquals("Task 2", result.get(1).getName());
    }

    @Test
    void testGetTaskByEmployeeId_Success() {
        UUID employeeId = UUID.randomUUID();
        Task mockTask = new Task();
        mockTask.setId(UUID.randomUUID());
        mockTask.setName("Mock Task");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task> mockResponse = new ResponseEntity<>(mockTask, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(TASK_SERVICE_URL + "/by-employee/" + employeeId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Task.class)))
                .thenReturn(mockResponse);

        Task result = taskService.getTaskByEmployeeId(employeeId, mockToken);

        assertNotNull(result);
        assertEquals("Mock Task", result.getName());
    }

    @Test
    void testGetTaskByEmployeeId_Unauthorized() {
        UUID employeeId = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(TASK_SERVICE_URL + "/by-employee/" + employeeId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Task.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> taskService.getTaskByEmployeeId(employeeId, mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }
}

