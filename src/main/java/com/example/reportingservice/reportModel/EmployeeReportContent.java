package com.example.reportingservice.reportModel;

import java.util.Map;
import java.util.Objects;

public class EmployeeReportContent {

    private String employeeId;
    private String employeeName;
    private String departmentName;
    private Map<String, String> task;

    public EmployeeReportContent(String employeeId, String employeeName, String departmentName, Map<String, String> task) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.task = task;
    }

    public EmployeeReportContent() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Map<String, String> getTask() {
        return task;
    }

    public void setTask(Map<String, String> task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeReportContent that = (EmployeeReportContent) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(employeeName, that.employeeName) && Objects.equals(departmentName, that.departmentName) && Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeName, departmentName, task);
    }

    @Override
    public String toString() {
        return "EmployeeReportContent{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", task=" + task +
                '}';
    }
}
