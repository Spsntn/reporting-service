package com.example.reportingservice.service;

import com.example.reportingservice.model.Report;
import com.example.reportingservice.repository.ReportRepository;
import com.example.reportingservice.service.ReportService;
import com.example.reportingservice.util.ReportContentGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportContentGenerator reportContentGenerator;

    @Test
    void testGenerateProjectsReport() throws Exception {
        String type = "project";
        String parameters = "param";
        String token = "mock-token";

        when(reportContentGenerator.generateProjectReportContent(token))
                .thenReturn("Mock project content");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock project content");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateProjectsReport(type, parameters, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock project content", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGenerateProjectReportByProjectId() throws Exception {
        String type = "project";
        String parameters = "param";
        UUID projectId = UUID.randomUUID();
        String token = "mock-token";

        when(reportContentGenerator.generateProjectReportContentByProjectId(projectId, token))
                .thenReturn("Mock project content for specific project");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock project content for specific project");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateProjectReportByProjectId(type, parameters, projectId, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock project content for specific project", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGenerateEmployeesReport() throws Exception {
        String type = "employee";
        String parameters = "param";
        String token = "mock-token";

        when(reportContentGenerator.generateEmployeeReportContent(token))
                .thenReturn("Mock employee content");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock employee content");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateEmployeesReport(type, parameters, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock employee content", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGenerateEmployeeReportByEmployeeId() throws Exception {
        String type = "employee";
        String parameters = "param";
        UUID employeeId = UUID.randomUUID();
        String token = "mock-token";

        when(reportContentGenerator.generateEmployeeReportContentByEmployeeId(employeeId, token))
                .thenReturn("Mock employee content for specific employee");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock employee content for specific employee");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateEmployeeReportByEmployeeId(type, parameters, employeeId, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock employee content for specific employee", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGenerateTasksReport() throws Exception {
        String type = "task";
        String parameters = "param";
        String token = "mock-token";

        when(reportContentGenerator.generateTasksReportContent(token))
                .thenReturn("Mock tasks content");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock tasks content");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateTasksReport(type, parameters, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock tasks content", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGenerateTaskReportByTaskId() throws Exception {
        String type = "task";
        String parameters = "param";
        UUID taskId = UUID.randomUUID();
        String token = "mock-token";

        when(reportContentGenerator.generateTaskReportContentByTaskId(taskId, token))
                .thenReturn("Mock task content for specific task");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock task content for specific task");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateTaskReportByTaskId(type, parameters, taskId, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock task content for specific task", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGenerateDepartmentsReport() throws Exception {
        String type = "department";
        String parameters = "param";
        String token = "mock-token";

        when(reportContentGenerator.generateDepartmentsReportContent(token))
                .thenReturn("Mock departments content");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock departments content");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateDepartmentsReport(type, parameters, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock departments content", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGenerateDepartmentReportByDepartmentId() throws Exception {
        String type = "department";
        String parameters = "param";
        UUID departmentId = UUID.randomUUID();
        String token = "mock-token";

        when(reportContentGenerator.generateDepartmentReportContentByDepartmentId(departmentId, token))
                .thenReturn("Mock department content for specific department");

        Report savedReport = new Report();
        savedReport.setId(UUID.randomUUID().toString());
        savedReport.setType(type);
        savedReport.setContent("Mock department content for specific department");

        when(reportRepository.save(any(Report.class))).thenReturn(savedReport);

        Report result = reportService.generateDepartmentReportByDepartmentId(type, parameters, departmentId, token);

        assertNotNull(result);
        assertEquals(type, result.getType());
        assertEquals("Mock department content for specific department", result.getContent());
        verify(reportRepository).save(any(Report.class));
    }

    @Test
    void testGetReportById() {
        String reportId = UUID.randomUUID().toString();
        Report mockReport = new Report();
        mockReport.setId(reportId);

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(mockReport));

        Report result = reportService.getReportById(reportId);

        assertNotNull(result);
        assertEquals(reportId, result.getId());
    }

    @Test
    void testGetReportByIdNotFound() {
        String reportId = UUID.randomUUID().toString();

        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        Report result = reportService.getReportById(reportId);

        assertNull(result);
    }

    @Test
    void testDeleteReport() {
        String reportId = UUID.randomUUID().toString();

        reportService.deleteReport(reportId);

        verify(reportRepository).deleteById(reportId);
    }
}
