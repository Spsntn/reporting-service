package com.example.reportingservice.service;

import com.example.reportingservice.model.Employee;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private RestTemplate restTemplate;

    private static final String EMPLOYEE_SERVICE_URL = "https://chow-choice-cicada.ngrok-free.app/api/employees";
    private static final String mockToken = "mock-token";

    @Test
    void testGetAllEmployees_Success() {
        Employee mockEmployee1 = new Employee();
        mockEmployee1.setId(UUID.randomUUID());
        mockEmployee1.setFirstName("John");
        mockEmployee1.setLastName("Doe");

        Employee mockEmployee2 = new Employee();
        mockEmployee2.setId(UUID.randomUUID());
        mockEmployee2.setFirstName("Jane");
        mockEmployee2.setLastName("Smith");

        Employee[] mockEmployeesArray = {mockEmployee1, mockEmployee2};
        List<Employee> mockEmployees = Arrays.asList(mockEmployeesArray);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Employee[]> mockResponse = new ResponseEntity<>(mockEmployeesArray, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(EMPLOYEE_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Employee[].class)))
                .thenReturn(mockResponse);

        List<Employee> result = employeeService.getAllEmployees(mockToken);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFirstName() +  " " + result.get(0).getLastName());
        assertEquals("Jane Smith", result.get(1).getFirstName() + " " + result.get(1).getLastName());
    }

    @Test
    void testGetAllEmployees_Unauthorized() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(EMPLOYEE_SERVICE_URL),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Employee[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> employeeService.getAllEmployees(mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void testGetEmployeesByDepartmentId_Success() {
        UUID departmentId = UUID.randomUUID();
        Employee mockEmployee1 = new Employee();
        mockEmployee1.setId(UUID.randomUUID());
        mockEmployee1.setFirstName("John");
        mockEmployee1.setLastName("Doe");

        Employee mockEmployee2 = new Employee();
        mockEmployee2.setId(UUID.randomUUID());
        mockEmployee2.setFirstName("Jane");
        mockEmployee2.setLastName("Smith");


        Employee[] mockEmployeesArray = {mockEmployee1, mockEmployee2};

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Employee[]> mockResponse = new ResponseEntity<>(mockEmployeesArray, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(EMPLOYEE_SERVICE_URL + "/by-department/" + departmentId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Employee[].class)))
                .thenReturn(mockResponse);

        List<Employee> result = employeeService.getEmployeesByDepartmentId(departmentId, mockToken);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFirstName() +  " " + result.get(0).getLastName());
        assertEquals("Jane Smith", result.get(1).getFirstName() + " " + result.get(1).getLastName());
    }

    @Test
    void testFindEmployeeById_Success() {
        UUID employeeId = UUID.randomUUID();
        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setFirstName("John");
        mockEmployee.setLastName("Doe");


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Employee> mockResponse = new ResponseEntity<>(mockEmployee, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(EMPLOYEE_SERVICE_URL + "/" + employeeId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Employee.class)))
                .thenReturn(mockResponse);

        Employee result = employeeService.findEmployeeById(employeeId, mockToken);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());
        assertEquals("John Doe", result.getFirstName() +  " " + result.getLastName());}

    @Test
    void testFindEmployeeById_Unauthorized() {
        UUID employeeId = UUID.randomUUID();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(mockToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        when(restTemplate.exchange(
                eq(EMPLOYEE_SERVICE_URL + "/" + employeeId),
                eq(HttpMethod.GET),
                eq(entity),
                eq(Employee.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> employeeService.findEmployeeById(employeeId, mockToken));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }
}

