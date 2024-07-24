package com.example.reportingservice.reportModel;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EmployeeReportContentTest {

    @Test
    void testDefaultConstructor() {
        EmployeeReportContent reportContent = new EmployeeReportContent();
        assertEquals(null, reportContent.getEmployeeId());
        assertEquals(null, reportContent.getEmployeeName());
        assertEquals(null, reportContent.getDepartmentName());
        assertEquals(null, reportContent.getTask());
    }

    @Test
    void testParameterizedConstructorAndGetterSetterMethods() {
        String employeeId = "12345";
        String employeeName = "John Doe";
        String departmentName = "IT Department";
        Map<String, String> task = new HashMap<>();
        task.put("taskName", "Fix bugs");
        task.put("status", "In Progress");

        EmployeeReportContent reportContent = new EmployeeReportContent();
        reportContent.setEmployeeId(employeeId);
        reportContent.setEmployeeName(employeeName);
        reportContent.setDepartmentName(departmentName);
        reportContent.setTask(task);

        assertEquals(employeeId, reportContent.getEmployeeId());
        assertEquals(employeeName, reportContent.getEmployeeName());
        assertEquals(departmentName, reportContent.getDepartmentName());
        assertEquals(task, reportContent.getTask());
    }

    @Test
    void testEqualsAndHashCode() {
        String employeeId = "12345";
        String employeeName = "John Doe";
        String departmentName = "IT Department";
        Map<String, String> task = new HashMap<>();
        task.put("taskName", "Fix bugs");
        task.put("status", "In Progress");

        EmployeeReportContent reportContent1 = new EmployeeReportContent(employeeId, employeeName, departmentName, task);
        EmployeeReportContent reportContent2 = new EmployeeReportContent(employeeId, employeeName, departmentName, task);
        EmployeeReportContent reportContent3 = new EmployeeReportContent();

        assertEquals(reportContent1, reportContent2);
        assertNotEquals(reportContent1, reportContent3);
        assertEquals(reportContent1.hashCode(), reportContent2.hashCode());
        assertNotEquals(reportContent1.hashCode(), reportContent3.hashCode());
    }

    @Test
    void testToString() {
        String employeeId = "12345";
        String employeeName = "John Doe";
        String departmentName = "IT Department";
        Map<String, String> task = new HashMap<>();
        task.put("taskName", "Fix bugs");
        task.put("status", "In Progress");

        EmployeeReportContent reportContent = new EmployeeReportContent(employeeId, employeeName, departmentName, task);

        String expectedToString = "EmployeeReportContent{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", task=" + task +
                '}';
        assertEquals(expectedToString, reportContent.toString());
    }
}
