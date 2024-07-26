package com.example.reportingservice.service;

import com.example.reportingservice.model.Employee;
import com.example.reportingservice.model.Project;
import com.example.reportingservice.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ProjectService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${project.service.url}")
    public String projectServiceUrl;

    public Project getProjectById(UUID id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Project> response = restTemplate.exchange(
                projectServiceUrl + "/" + id,
                HttpMethod.GET,
                entity,
                Project.class
        );

        return response.getBody();
    }

    public List<Project> getProjectByDepartmentId(UUID id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // Aggiungi il token alla richiesta
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Project>> response = restTemplate.exchange(
                projectServiceUrl + "/by-department/" + id,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Project>>() {}
        );
        return response.getBody();
    }

    public List<Project> getAllProjects(String token) {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token); // Aggiungi il token alla richiesta
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Log della richiesta
            System.out.println("Sending request to: " + projectServiceUrl);
            System.out.println("Token: " + token);

            ResponseEntity<Project[]> response;
            try {
                response = restTemplate.exchange(
                        projectServiceUrl,
                        HttpMethod.GET,
                        entity,
                        Project[].class
                );
            } catch (HttpClientErrorException e) {
                // Log dell'errore
                System.err.println("Error: " + e.getStatusCode() + " - " + e.getStatusText());
                throw e;
            }

            // Estrai la lista di Task dall'entità di risposta
            Project[] tasksArray = response.getBody();
            return Arrays.asList(tasksArray);

    }



}
