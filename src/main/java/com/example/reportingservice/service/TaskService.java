package com.example.reportingservice.service;

import com.example.reportingservice.model.Project;
import com.example.reportingservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String TASK_SERVICE_URL = "https://hedgehog-open-dolphin.ngrok-free.app/api/tasks";

    public List<Task> getAlltasks(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Log della richiesta
        System.out.println("Sending request to: " + TASK_SERVICE_URL);
        System.out.println("Token: " + token);

        ResponseEntity<Task[]> response;
        try {
            response = restTemplate.exchange(
                    TASK_SERVICE_URL,
                    HttpMethod.GET,
                    entity,
                    Task[].class
            );
        } catch (HttpClientErrorException e) {
            // Log dell'errore
            System.err.println("Error: " + e.getStatusCode() + " - " + e.getStatusText());
            throw e;
        }

        // Estrai la lista di Task dall'entità di risposta
        Task[] tasksArray = response.getBody();
        return Arrays.asList(tasksArray);
    }

    public Task getTaskById(UUID taskId,String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task> response = restTemplate.exchange(
                TASK_SERVICE_URL + "/" + taskId,
                HttpMethod.GET,
                entity,
                Task.class
        );

        return response.getBody();
    }

    public List<Task> getTasksByProjectId(UUID projectId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = TASK_SERVICE_URL + "/by-project/" + projectId;
        ResponseEntity<List<Task>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Task>>() {}
        );
        return response.getBody();
    }

    public Task getTaskByEmployeeId(UUID employeeId, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Task> response = restTemplate.exchange(
                TASK_SERVICE_URL + "/by-employee/" + employeeId,
                HttpMethod.GET,
                entity,
                Task.class
        );
        return response.getBody();
    }

}