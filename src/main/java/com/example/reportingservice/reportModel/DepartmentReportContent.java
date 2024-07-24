package com.example.reportingservice.reportModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DepartmentReportContent {

    private String departmentId;
    private String departmentName;
    private List<Map<String, String>> projects;
    private List<Map<String, String>> employees;

    public DepartmentReportContent(String departmentId, String departmentName, List<Map<String, String>> projects, List<Map<String, String>> employees) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.projects = projects;
        this.employees = employees;
    }

    public DepartmentReportContent() {
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Map<String, String>> getProjects() {
        return projects;
    }

    public void setProjects(List<Map<String, String>> projectName) {
        this.projects = projectName;
    }

    public List<Map<String, String>> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Map<String, String>> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentReportContent that = (DepartmentReportContent) o;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(departmentName, that.departmentName) && Objects.equals(projects, that.projects) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName, projects, employees);
    }

    @Override
    public String toString() {
        return "DepartmentReportContent{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", projects=" + projects +
                ", employees=" + employees +
                '}';
    }
}
