package com.example.reportingservice.reportModel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectReportContentTest {

    @Test
    void testDefaultConstructor() {
        ProjectReportContent reportContent = new ProjectReportContent();
        assertEquals(null, reportContent.getProjectId());
        assertEquals(null, reportContent.getProjectName());
        assertEquals(null, reportContent.getDepartmentName());
        assertEquals(null, reportContent.getTasks());
    }

    @Test
    void testParameterizedConstructorAndGetterSetterMethods() {
        String projectId = "98765";
        String projectName = "Project X";
        String departmentName = "IT Department";

        List<Map<String, String>> tasks = new ArrayList<>();
        Map<String, String> taskInfo = new HashMap<>();
        taskInfo.put("taskId", "1");
        taskInfo.put("taskName", "Task A");
        tasks.add(taskInfo);

        ProjectReportContent reportContent = new ProjectReportContent(projectId, projectName, departmentName, tasks);

        assertEquals(projectId, reportContent.getProjectId());
        assertEquals(projectName, reportContent.getProjectName());
        assertEquals(departmentName, reportContent.getDepartmentName());
        assertEquals(tasks, reportContent.getTasks());
    }

    @Test
    void testEqualsAndHashCode() {
        String projectId = "98765";
        String projectName = "Project X";
        String departmentName = "IT Department";

        List<Map<String, String>> tasks = new ArrayList<>();
        Map<String, String> taskInfo = new HashMap<>();
        taskInfo.put("taskId", "1");
        taskInfo.put("taskName", "Task A");
        tasks.add(taskInfo);

        ProjectReportContent reportContent1 = new ProjectReportContent(projectId, projectName, departmentName, tasks);
        ProjectReportContent reportContent2 = new ProjectReportContent(projectId, projectName, departmentName, tasks);
        ProjectReportContent reportContent3 = new ProjectReportContent();

        assertEquals(reportContent1, reportContent2);
        assertNotEquals(reportContent1, reportContent3);
        assertEquals(reportContent1.hashCode(), reportContent2.hashCode());
        assertNotEquals(reportContent1.hashCode(), reportContent3.hashCode());
    }

    @Test
    void testToString() {
        String projectId = "98765";
        String projectName = "Project X";
        String departmentName = "IT Department";

        List<Map<String, String>> tasks = new ArrayList<>();
        Map<String, String> taskInfo = new HashMap<>();
        taskInfo.put("taskId", "1");
        taskInfo.put("taskName", "Task A");
        tasks.add(taskInfo);

        ProjectReportContent reportContent = new ProjectReportContent(projectId, projectName, departmentName, tasks);

        String expectedToString = "ProjectReportContent{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", tasks=" + tasks +
                '}';
        assertEquals(expectedToString, reportContent.toString());
    }
}
