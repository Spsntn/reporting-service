package com.example.reportingservice.reportModel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DepartmentReportContentTest {

    @Test
    void testDefaultConstructor() {
        DepartmentReportContent reportContent = new DepartmentReportContent();
        assertEquals(null, reportContent.getDepartmentId());
        assertEquals(null, reportContent.getDepartmentName());
        assertEquals(null, reportContent.getProjects());
        assertEquals(null, reportContent.getEmployees());
    }

    @Test
    void testParameterizedConstructorAndGetterSetterMethods() {
        String departmentId = "12345";
        String departmentName = "IT Department";

        List<Map<String, String>> projects = new ArrayList<>();
        Map<String, String> projectInfo = new HashMap<>();
        projectInfo.put("projectId", "1");
        projectInfo.put("projectName", "Project A");
        projects.add(projectInfo);

        List<Map<String, String>> employees = new ArrayList<>();
        Map<String, String> employeeInfo = new HashMap<>();
        employeeInfo.put("employeeId", "1001");
        employeeInfo.put("employeeName", "John Doe");
        employees.add(employeeInfo);

        DepartmentReportContent reportContent = new DepartmentReportContent(departmentId, departmentName, projects, employees);

        assertEquals(departmentId, reportContent.getDepartmentId());
        assertEquals(departmentName, reportContent.getDepartmentName());
        assertEquals(projects, reportContent.getProjects());
        assertEquals(employees, reportContent.getEmployees());
    }

    @Test
    void testEqualsAndHashCode() {
        String departmentId = "12345";
        String departmentName = "IT Department";

        List<Map<String, String>> projects = new ArrayList<>();
        Map<String, String> projectInfo = new HashMap<>();
        projectInfo.put("projectId", "1");
        projectInfo.put("projectName", "Project A");
        projects.add(projectInfo);

        List<Map<String, String>> employees = new ArrayList<>();
        Map<String, String> employeeInfo = new HashMap<>();
        employeeInfo.put("employeeId", "1001");
        employeeInfo.put("employeeName", "John Doe");
        employees.add(employeeInfo);

        DepartmentReportContent reportContent1 = new DepartmentReportContent(departmentId, departmentName, projects, employees);
        DepartmentReportContent reportContent2 = new DepartmentReportContent(departmentId, departmentName, projects, employees);
        DepartmentReportContent reportContent3 = new DepartmentReportContent();

        assertEquals(reportContent1, reportContent2);
        assertNotEquals(reportContent1, reportContent3);
        assertEquals(reportContent1.hashCode(), reportContent2.hashCode());
        assertNotEquals(reportContent1.hashCode(), reportContent3.hashCode());
    }

    @Test
    void testToString() {
        String departmentId = "12345";
        String departmentName = "IT Department";

        List<Map<String, String>> projects = new ArrayList<>();
        Map<String, String> projectInfo = new HashMap<>();
        projectInfo.put("projectId", "1");
        projectInfo.put("projectName", "Project A");
        projects.add(projectInfo);

        List<Map<String, String>> employees = new ArrayList<>();
        Map<String, String> employeeInfo = new HashMap<>();
        employeeInfo.put("employeeId", "1001");
        employeeInfo.put("employeeName", "John Doe");
        employees.add(employeeInfo);

        DepartmentReportContent reportContent = new DepartmentReportContent(departmentId, departmentName, projects, employees);

        String expectedToString = "DepartmentReportContent{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", projects=" + projects +
                ", employees=" + employees +
                '}';
        assertEquals(expectedToString, reportContent.toString());
    }
}
