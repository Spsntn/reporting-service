package com.example.reportingservice.reportModel;

import java.util.Objects;

public class TaskReportContent {

        private String taskId;
        private String taskName;
        private String ProjectName;
        private String Description;
        private String EmployeeName;

    public TaskReportContent(String taskId, String taskName, String projectName, String description, String employeeName) {
        this.taskId = taskId;
        this.taskName = taskName;
        ProjectName = projectName;
        Description = description;
        EmployeeName = employeeName;
    }

    public TaskReportContent() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskReportContent that = (TaskReportContent) o;
        return Objects.equals(taskId, that.taskId) && Objects.equals(taskName, that.taskName) && Objects.equals(ProjectName, that.ProjectName) && Objects.equals(Description, that.Description) && Objects.equals(EmployeeName, that.EmployeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, ProjectName, Description, EmployeeName);
    }

    @Override
    public String toString() {
        return "TaskReportContent{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", Description='" + Description + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                '}';
    }
}
