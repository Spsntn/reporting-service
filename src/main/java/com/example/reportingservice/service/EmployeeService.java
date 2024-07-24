package com.example.reportingservice.service;

import com.example.reportingservice.model.Department;
import com.example.reportingservice.model.Employee;
import com.example.reportingservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String EMPLOYEE_SERVICE_URL = "https://chow-choice-cicada.ngrok-free.app/api/employees";

    public List<Employee> getAllEmployees(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Employee[]> response = restTemplate.exchange(
                EMPLOYEE_SERVICE_URL,
                HttpMethod.GET,
                entity,
                Employee[].class
        );
        // Estrai la lista di Task dall'entità di risposta
        Employee[] employeeArray = response.getBody();
        List<Employee> employees = Arrays.asList(employeeArray);
        return employees;
    }

    public List<Employee> getEmployeesByDepartmentId(UUID id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Employee[]> response = restTemplate.exchange(
                EMPLOYEE_SERVICE_URL + "/by-department/" + id,
                HttpMethod.GET,
                entity,
                Employee[].class
        );


        // Estrai la lista di Task dall'entità di risposta
        Employee[] employeeArray = response.getBody();
        return Arrays.asList(employeeArray);
    }

    public Employee findEmployeeById(UUID id, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Employee> response = restTemplate.exchange(
                EMPLOYEE_SERVICE_URL + "/" + id,
                HttpMethod.GET,
                entity,
                Employee.class
        );

        return response.getBody();
    }
}
