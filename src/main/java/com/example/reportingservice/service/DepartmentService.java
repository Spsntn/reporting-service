package com.example.reportingservice.service;

import com.example.reportingservice.filter.JwtRequestFilter;
import com.example.reportingservice.model.Department;
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
public class DepartmentService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String DEPARTMENT_SERVICE_URL = "https://verified-rich-maggot.ngrok-free.app/api/departments";

    public Department getDepartmentById(UUID id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Department> response = restTemplate.exchange(
                DEPARTMENT_SERVICE_URL + "/" + id,
                HttpMethod.GET,
                entity,
                Department.class
        );
        return response.getBody();
    }

    public List<Department> getAllDepartments(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Department[]> response = restTemplate.exchange(
                DEPARTMENT_SERVICE_URL,
                HttpMethod.GET,
                entity,
                Department[].class
        );
        Department[] departmentsArray = response.getBody();
        List<Department> departments = Arrays.asList(departmentsArray);
        return departments;
    }
}
