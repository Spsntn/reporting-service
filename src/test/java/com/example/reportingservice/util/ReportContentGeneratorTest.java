package com.example.reportingservice.util;

import com.example.reportingservice.model.*;
import com.example.reportingservice.reportModel.DepartmentReportContent;
import com.example.reportingservice.reportModel.EmployeeReportContent;
import com.example.reportingservice.reportModel.ProjectReportContent;
import com.example.reportingservice.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportContentGeneratorTest {

    @InjectMocks
    private ReportContentGenerator reportContentGenerator;

    @Mock
    private ProjectService projectService;

    @Mock
    private TaskService taskService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void testGenerateProjectReportContent() throws Exception {
        String token = "mock-token";
        Project mockProject = new Project();
        mockProject.setId(UUID.randomUUID());
        mockProject.setName("Test Project");
        mockProject.setIdDepartment(UUID.randomUUID());

        Department mockDepartment = new Department();
        mockDepartment.setName("Test Department");

        Task mockTask = new Task();
        mockTask.setName("Test Task");
        mockTask.setStatus("In Progress");
        mockTask.setEmployeeId(UUID.randomUUID());

        Employee mockEmployee = new Employee();
        mockEmployee.setFirstName("John");
        mockEmployee.setLastName("Doe");

        when(projectService.getAllProjects(anyString())).thenReturn(Collections.singletonList(mockProject));
        when(departmentService.getDepartmentById(any(UUID.class), anyString())).thenReturn(mockDepartment);
        when(taskService.getTasksByProjectId(any(UUID.class), anyString())).thenReturn(Collections.singletonList(mockTask));
        when(employeeService.findEmployeeById(any(UUID.class), anyString())).thenReturn(mockEmployee);

        String mockJson = "[{\"projectId\":\"" + mockProject.getId() + "\",\"projectName\":\"Test Project\",\"departmentName\":\"Test Department\",\"tasks\":[{\"taskName\":\"Test Task\",\"employeeName\":\"John Doe\",\"status\":\"In Progress\"}]}]";
        when(objectMapper.writeValueAsString(any())).thenReturn(mockJson);

        String result = reportContentGenerator.generateProjectReportContent(token);

        assertNotNull(result);
        assertEquals(mockJson, result);
    }

    @Test
    void testGenerateEmployeeReportContent() throws Exception {
        String token = "mock-token";
        Employee mockEmployee = new Employee();
        mockEmployee.setId(UUID.randomUUID());
        mockEmployee.setFirstName("Jane");
        mockEmployee.setLastName("Doe");
        mockEmployee.setIdDepartment(UUID.randomUUID());

        Department mockDepartment = new Department();
        mockDepartment.setName("HR Department");

        Task mockTask = new Task();
        mockTask.setName("HR Task");
        mockTask.setStatus("Completed");

        when(employeeService.getAllEmployees(anyString())).thenReturn(Collections.singletonList(mockEmployee));
        when(departmentService.getDepartmentById(any(UUID.class), anyString())).thenReturn(mockDepartment);
        when(taskService.getTaskByEmployeeId(any(UUID.class), anyString())).thenReturn(mockTask);

        String mockJson = "[{\"employeeId\":\"" + mockEmployee.getId() + "\",\"employeeName\":\"Jane Doe\",\"departmentName\":\"HR Department\",\"task\":{\"taskName\":\"HR Task\",\"status\":\"Completed\"}}]";
        when(objectMapper.writeValueAsString(any())).thenReturn(mockJson);

        String result = reportContentGenerator.generateEmployeeReportContent(token);

        assertNotNull(result);
        assertEquals(mockJson, result);
    }


    @Test
    void testGenerateTasksReportContent() throws Exception {
        String token = "mock-token";
        Task mockTask = new Task();
        mockTask.setId(UUID.randomUUID());
        mockTask.setName("Important Task");
        mockTask.setProjectId(UUID.randomUUID());
        mockTask.setEmployeeId(UUID.randomUUID());
        mockTask.setDescription("Task description");
        mockTask.setStatus("In Progress");

        Project mockProject = new Project();
        mockProject.setName("Critical Project");

        Employee mockEmployee = new Employee();
        mockEmployee.setFirstName("Alice");
        mockEmployee.setLastName("Smith");

        when(taskService.getAlltasks(anyString())).thenReturn(Collections.singletonList(mockTask));
        when(projectService.getProjectById(any(UUID.class), anyString())).thenReturn(mockProject);
        when(employeeService.findEmployeeById(any(UUID.class), anyString())).thenReturn(mockEmployee);

        String mockJson = "[{\"taskId\":\"" + mockTask.getId() + "\",\"taskName\":\"Important Task\",\"projectName\":\"Critical Project\",\"employeeName\":\"Alice Smith\",\"description\":\"Task description\"}]";
        when(objectMapper.writeValueAsString(any())).thenReturn(mockJson);

        String result = reportContentGenerator.generateTasksReportContent(token);

        assertNotNull(result);
        assertEquals(mockJson, result);
    }

    @Test
    void testGenerateDepartmentsReportContent() throws JsonProcessingException, JsonProcessingException {
        // Mock data
        String token = "mock-token";
        UUID departmentId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();
        UUID employeeId = UUID.randomUUID();

        Department mockDepartment = new Department();
        mockDepartment.setId(departmentId);
        mockDepartment.setName("Marketing Department");

        Project mockProject = new Project();
        mockProject.setId(projectId);
        mockProject.setName("Critical Project");
        mockProject.setIdDepartment(departmentId);

        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setFirstName("Alice");
        mockEmployee.setLastName("Smith");
        mockEmployee.setIdDepartment(departmentId);

        Task mockTask = new Task();
        mockTask.setId(UUID.randomUUID());
        mockTask.setName("Important Task");
        mockTask.setProjectId(projectId);
        mockTask.setEmployeeId(employeeId);
        mockTask.setDescription("Task description");
        mockTask.setStatus("In Progress");

        // Mocking behavior of service methods
        when(departmentService.getAllDepartments(anyString())).thenReturn(Collections.singletonList(mockDepartment));
        when(projectService.getProjectByDepartmentId(any(UUID.class), anyString())).thenReturn(Collections.singletonList(mockProject));
        when(employeeService.getEmployeesByDepartmentId(any(UUID.class), anyString())).thenReturn(Collections.singletonList(mockEmployee));
        when(taskService.getTaskByEmployeeId(any(UUID.class), anyString())).thenReturn(mockTask);
        when(projectService.getProjectById(any(UUID.class), anyString())).thenReturn(mockProject);

        // Mocking behavior of objectMapper
        DepartmentReportContent mockDepartmentReportContent = new DepartmentReportContent();
        mockDepartmentReportContent.setDepartmentId(mockDepartment.getId().toString());
        mockDepartmentReportContent.setDepartmentName(mockDepartment.getName());
        List<Map<String, String>> projects = new ArrayList<>();
        Map<String, String> projectInfo = new HashMap<>();
        projectInfo.put("projectId", mockProject.getId().toString());
        projectInfo.put("ProjectName", mockProject.getName());
        projects.add(projectInfo);
        mockDepartmentReportContent.setProjects(projects);

        List<Map<String, String>> employees = new ArrayList<>();
        Map<String, String> employeeInfo = new HashMap<>();
        employeeInfo.put("employeeName", mockEmployee.getFirstName() + " " + mockEmployee.getLastName());
        employeeInfo.put("taskProject", mockProject.getName());
        employeeInfo.put("taskName", mockTask.getName());
        employeeInfo.put("status", mockTask.getStatus());
        employees.add(employeeInfo);
        mockDepartmentReportContent.setEmployees(employees);

        String mockJson = "[{\"departmentId\":\"" + mockDepartment.getId() + "\",\"departmentName\":\"Marketing Department\",\"projects\":[{\"projectId\":\"" + mockProject.getId() + "\",\"ProjectName\":\"Critical Project\"}],\"employees\":[{\"employeeName\":\"Alice Smith\",\"taskProject\":\"Critical Project\",\"taskName\":\"Important Task\",\"status\":\"In Progress\"}]}]";
        when(objectMapper.writeValueAsString(any())).thenReturn(mockJson);

        // Call the method under test
        String result = reportContentGenerator.generateDepartmentsReportContent(token);

        // Assertions
        assertNotNull(result);
        assertEquals(mockJson, result);
    }

    @Test
    void testGenerateProjectReportContentByProjectId() throws Exception {
        UUID projectId = UUID.randomUUID();
        String token = "mock-token";

        Project mockProject = new Project();
        mockProject.setId(projectId);
        mockProject.setName("Test Project");

        ProjectReportContent mockProjectReportContent = new ProjectReportContent();
        mockProjectReportContent.setProjectId(projectId.toString());
        mockProjectReportContent.setProjectName(mockProject.getName());

        // Mocking service method
        when(projectService.getProjectById(projectId, token)).thenReturn(mockProject);
        when(objectMapper.writeValueAsString(any(ProjectReportContent.class))).thenReturn("{\"projectId\":\"" + projectId + "\",\"projectName\":\"Test Project\"}");

        // Call the method under test
        String result = reportContentGenerator.generateProjectReportContentByProjectId(projectId, token);

        // Assert
        assertEquals("{\"projectId\":\"" + projectId + "\",\"projectName\":\"Test Project\"}", result);
    }

    @Test
    void testGenerateEmployeeReportContentByEmployeeId() throws Exception {
        UUID employeeId = UUID.randomUUID();
        String token = "mock-token";

        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setFirstName("John");
        mockEmployee.setLastName("Doe");

        EmployeeReportContent mockEmployeeReportContent = new EmployeeReportContent();
        mockEmployeeReportContent.setEmployeeId(employeeId.toString());
        mockEmployeeReportContent.setEmployeeName(mockEmployee.getFirstName() + " " + mockEmployee.getLastName());

        // Mocking service method
        when(employeeService.findEmployeeById(employeeId, token)).thenReturn(mockEmployee);
        when(objectMapper.writeValueAsString(any(EmployeeReportContent.class))).thenReturn("{\"employeeId\":\"" + employeeId + "\",\"employeeName\":\"John Doe\"}");

        // Call the method under test
        String result = reportContentGenerator.generateEmployeeReportContentByEmployeeId(employeeId, token);

        // Assert
        assertEquals("{\"employeeId\":\"" + employeeId + "\",\"employeeName\":\"John Doe\"}", result);
    }
}