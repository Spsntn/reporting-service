package com.example.reportingservice.controller;

import com.example.reportingservice.controller.ReportController;
import com.example.reportingservice.dto.GenerateReportRequest;
import com.example.reportingservice.model.Report;
import com.example.reportingservice.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    public void testGenerateProjectReport_Success() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("project");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("project");
        mockReport.setContent("Report content");

        when(reportService.generateProjectsReport(anyString(), anyString(), anyString()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/project")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("project"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateProjectReport_InternalServerError() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("project");
        request.setParameters("parameter1");

        when(reportService.generateProjectsReport(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/project")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGenerateTaskReport_Success() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("task");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("task");
        mockReport.setContent("Report content");

        when(reportService.generateTasksReport(anyString(), anyString(), anyString()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/task")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("task"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateTaskReport_InternalServerError() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("task");
        request.setParameters("parameter1");

        when(reportService.generateTasksReport(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/task")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGenerateDepartmentReportByDepartmentId_Success() throws Exception {
        UUID departmentId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("department");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("department");
        mockReport.setContent("Report content");

        when(reportService.generateDepartmentReportByDepartmentId(anyString(), anyString(), eq(departmentId), anyString()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/department/{id}", departmentId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("department"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateDepartmentReportByDepartmentId_InternalServerError() throws Exception {
        UUID departmentId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("department");
        request.setParameters("parameter1");

        when(reportService.generateDepartmentReportByDepartmentId(anyString(), anyString(), eq(departmentId), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/department/{id}", departmentId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGenerateDepartmentReport_Success() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("department");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("department");
        mockReport.setContent("Report content");

        when(reportService.generateDepartmentsReport(anyString(), anyString(), any()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/department")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("department"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateDepartmentReport_InternalServerError() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("department");
        request.setParameters("parameter1");

        when(reportService.generateDepartmentsReport(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/department")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGenerateProjectReportByProjectId_Success() throws Exception {
        UUID projectId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("project");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("project");
        mockReport.setContent("Report content");

        when(reportService.generateProjectReportByProjectId(anyString(), anyString(), eq(projectId), anyString()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/project/{id}", projectId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("project"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateProjectReportByProjectId_InternalServerError() throws Exception {
        UUID projectId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("project");
        request.setParameters("parameter1");

        when(reportService.generateProjectReportByProjectId(anyString(), anyString(), eq(projectId), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/project/{id}", projectId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGenerateTaskReportByTaskId_Success() throws Exception {
        UUID taskId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("task");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("task");
        mockReport.setContent("Report content");

        when(reportService.generateTaskReportByTaskId(anyString(), anyString(), eq(taskId), anyString()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/task/{id}", taskId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("task"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateTaskReportByTaskId_InternalServerError() throws Exception {
        UUID taskId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("task");
        request.setParameters("parameter1");

        when(reportService.generateTaskReportByTaskId(anyString(), anyString(), eq(taskId), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/task/{id}", taskId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGenerateEmployeeReportByEmployeeId_Success() throws Exception {
        UUID employeeId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("employee");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("employee");
        mockReport.setContent("Report content");

        when(reportService.generateEmployeeReportByEmployeeId(anyString(), anyString(), eq(employeeId), anyString()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/employee/{id}", employeeId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("employee"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateEmployeeReportByEmployeeId_InternalServerError() throws Exception {
        UUID employeeId = UUID.randomUUID();
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("employee");
        request.setParameters("parameter1");

        when(reportService.generateEmployeeReportByEmployeeId(anyString(), anyString(), eq(employeeId), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/employee/{id}", employeeId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGenerateEmployeesReport_Success() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("employee");
        request.setParameters("parameter1");

        Report mockReport = new Report();
        mockReport.setId(UUID.randomUUID().toString());
        mockReport.setType("employee");
        mockReport.setContent("Report content");

        when(reportService.generateEmployeesReport(anyString(), anyString(), anyString()))
                .thenReturn(mockReport);

        mockMvc.perform(post("/api/reports/employee")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(mockReport.getId().toString()))
                .andExpect(jsonPath("$.type").value("employee"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGenerateEmployeesReport_InternalServerError() throws Exception {
        GenerateReportRequest request = new GenerateReportRequest();
        request.setType("employee");
        request.setParameters("parameter1");

        when(reportService.generateEmployeesReport(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(post("/api/reports/employee")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mock-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetReportById_Success() throws Exception {
        UUID reportId = UUID.randomUUID();
        Report mockReport = new Report();
        mockReport.setId(reportId.toString());
        mockReport.setType("project");
        mockReport.setContent("Report content");

        when(reportService.getReportById(reportId.toString()))
                .thenReturn(mockReport);

        mockMvc.perform(get("/api/reports/{id}", reportId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reportId.toString()))
                .andExpect(jsonPath("$.type").value("project"))
                .andExpect(jsonPath("$.content").value("Report content"));
    }

    @Test
    public void testGetReportById_NotFound() throws Exception {
        String reportId = "invalid-id";

        when(reportService.getReportById(reportId))
                .thenReturn(null);

        mockMvc.perform(get("/api/reports/{id}", reportId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteReport_Success() throws Exception {
        String reportId = "valid-id";

        doNothing().when(reportService).deleteReport(reportId);

        mockMvc.perform(delete("/api/reports/{id}", reportId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testSayHello() throws Exception {
        mockMvc.perform(get("/api/reports"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
