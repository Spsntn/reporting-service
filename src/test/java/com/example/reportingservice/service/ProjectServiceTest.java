package com.example.reportingservice.service;

import com.example.reportingservice.model.Project;
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
class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private RestTemplate restTemplate;

    private static final String PROJECT_SERVICE_URL = "https://monarch-careful-marmoset.ngrok-free.app/api/projects";
    private static final String mockToken = "mock-token";

    @Test
    void testGetProjectById_Success() {
        UUID projectId = UUID.randomUUID();
        Project mockProject = new Project();
        mockProject.setId(projectId);
        mockProject.setName("Mock Project");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Project> mockResponse = new ResponseEntity<>(mockProject, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(PROJECT_SERVICE_URL + "/" + projectId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Project.class)))
                .thenReturn(mockResponse);

        Project result = projectService.getProjectById(projectId, mockToken);

        assertNotNull(result);
        assertEquals(projectId, result.getId());
        assertEquals("Mock Project", result.getName());
    }

    @Test
    void testGetProjectById_Unauthorized() {
        UUID projectId = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(PROJECT_SERVICE_URL + "/" + projectId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Project.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> projectService.getProjectById(projectId, mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testGetProjectByDepartmentId_Success() {
        UUID departmentId = UUID.randomUUID();
        Project mockProject1 = new Project();
        mockProject1.setId(UUID.randomUUID());
        mockProject1.setName("Project 1");

        Project mockProject2 = new Project();
        mockProject2.setId(UUID.randomUUID());
        mockProject2.setName("Project 2");

        List<Project> mockProjects = Arrays.asList(mockProject1, mockProject2);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Project>> mockResponse = new ResponseEntity<>(mockProjects, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(PROJECT_SERVICE_URL + "/by-department/" + departmentId),
                eq(HttpMethod.GET),
                eq(entity),
                any(ParameterizedTypeReference.class)))
                .thenReturn(mockResponse);

        List<Project> result = projectService.getProjectByDepartmentId(departmentId, mockToken);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getName());
        assertEquals("Project 2", result.get(1).getName());
    }

    @Test
    void testGetAllProjects_Success() {
        Project mockProject1 = new Project();
        mockProject1.setId(UUID.randomUUID());
        mockProject1.setName("Project 1");

        Project mockProject2 = new Project();
        mockProject2.setId(UUID.randomUUID());
        mockProject2.setName("Project 2");

        Project[] mockProjectsArray = {mockProject1, mockProject2};
        List<Project> mockProjects = Arrays.asList(mockProjectsArray);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Project[]> mockResponse = new ResponseEntity<>(mockProjectsArray, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(PROJECT_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Project[].class)))
                .thenReturn(mockResponse);

        List<Project> result = projectService.getAllProjects(mockToken);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getName());
        assertEquals("Project 2", result.get(1).getName());
    }

    @Test
    void testGetAllProjects_Unauthorized() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(PROJECT_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Project[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> projectService.getAllProjects(mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }
}

