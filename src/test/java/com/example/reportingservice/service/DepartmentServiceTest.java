package com.example.reportingservice.service;

import com.example.reportingservice.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private RestTemplate restTemplate;

    private static final String DEPARTMENT_SERVICE_URL = "https://verified-rich-maggot.ngrok-free.app/api/departments";
    private static final String mockToken = "mock-token";

    @Test
    void testGetDepartmentById_Success() {
        UUID departmentId = UUID.randomUUID();
        Department mockDepartment = new Department();
        mockDepartment.setId(departmentId);
        mockDepartment.setName("Mock Department");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Department> mockResponse = new ResponseEntity<>(mockDepartment, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(DEPARTMENT_SERVICE_URL + "/" + departmentId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Department.class)))
                .thenReturn(mockResponse);

        Department result = departmentService.getDepartmentById(departmentId, mockToken);

        assertNotNull(result);
        assertEquals(departmentId, result.getId());
        assertEquals("Mock Department", result.getName());
    }

    @Test
    void testGetDepartmentById_Unauthorized() {
        UUID departmentId = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(DEPARTMENT_SERVICE_URL + "/" + departmentId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Department.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> departmentService.getDepartmentById(departmentId, mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testGetAllDepartments_Success() {
        Department mockDepartment1 = new Department();
        mockDepartment1.setId(UUID.randomUUID());
        mockDepartment1.setName("Department 1");

        Department mockDepartment2 = new Department();
        mockDepartment2.setId(UUID.randomUUID());
        mockDepartment2.setName("Department 2");

        Department[] mockDepartmentsArray = {mockDepartment1, mockDepartment2};
        List<Department> mockDepartments = Arrays.asList(mockDepartmentsArray);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Department[]> mockResponse = new ResponseEntity<>(mockDepartmentsArray, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(DEPARTMENT_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Department[].class)))
                .thenReturn(mockResponse);

        List<Department> result = departmentService.getAllDepartments(mockToken);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Department 1", result.get(0).getName());
        assertEquals("Department 2", result.get(1).getName());
    }

    @Test
    void testGetAllDepartments_Unauthorized() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(DEPARTMENT_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Department[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> departmentService.getAllDepartments(mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }
}

