package com.example.reportingservice.reportModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProjectReportContent {

    private String projectId;
    private String projectName;
    private String departmentName;
    private List<Map<String, String>> tasks;

    public ProjectReportContent(String projectId, String projectName, String departmentName, List<Map<String, String>> tasks) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.departmentName = departmentName;
        this.tasks = tasks;
    }

    public ProjectReportContent() {
    }

    // Getters and Setters
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Map<String, String>> getTasks() {
        return tasks;
    }

    public void setTasks(List<Map<String, String>> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectReportContent that = (ProjectReportContent) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectName, that.projectName) && Objects.equals(departmentName, that.departmentName) && Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, departmentName, tasks);
    }

    @Override
    public String toString() {
        return "ProjectReportContent{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
