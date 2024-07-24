package com.example.reportingservice.reportModel;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskReportContentTest {

    @Test
    void testDefaultConstructor() {
        TaskReportContent reportContent = new TaskReportContent();
        assertEquals(null, reportContent.getTaskId());
        assertEquals(null, reportContent.getTaskName());
        assertEquals(null, reportContent.getProjectName());
        assertEquals(null, reportContent.getDescription());
        assertEquals(null, reportContent.getEmployeeName());
    }

    @Test
    void testParameterizedConstructorAndGetterSetterMethods() {
        String taskId = "12345";
        String taskName = "Task A";
        String projectName = "Project X";
        String description = "Task description";
        String employeeName = "John Doe";

        TaskReportContent reportContent = new TaskReportContent(taskId, taskName, projectName, description, employeeName);

        assertEquals(taskId, reportContent.getTaskId());
        assertEquals(taskName, reportContent.getTaskName());
        assertEquals(projectName, reportContent.getProjectName());
        assertEquals(description, reportContent.getDescription());
        assertEquals(employeeName, reportContent.getEmployeeName());
    }

    @Test
    void testEqualsAndHashCode() {
        String taskId = "12345";
        String taskName = "Task A";
        String projectName = "Project X";
        String description = "Task description";
        String employeeName = "John Doe";

        TaskReportContent reportContent1 = new TaskReportContent(taskId, taskName, projectName, description, employeeName);
        TaskReportContent reportContent2 = new TaskReportContent(taskId, taskName, projectName, description, employeeName);
        TaskReportContent reportContent3 = new TaskReportContent();

        assertEquals(reportContent1, reportContent2);
        assertNotEquals(reportContent1, reportContent3);
        assertEquals(reportContent1.hashCode(), reportContent2.hashCode());
        assertNotEquals(reportContent1.hashCode(), reportContent3.hashCode());
    }

    @Test
    void testToString() {
        String taskId = "12345";
        String taskName = "Task A";
        String projectName = "Project X";
        String description = "Task description";
        String employeeName = "John Doe";

        TaskReportContent reportContent = new TaskReportContent(taskId, taskName, projectName, description, employeeName);

        String expectedToString = "TaskReportContent{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", ProjectName='" + projectName + '\'' +
                ", Description='" + description + '\'' +
                ", EmployeeName='" + employeeName + '\'' +
                '}';
        assertEquals(expectedToString, reportContent.toString());
    }
}
