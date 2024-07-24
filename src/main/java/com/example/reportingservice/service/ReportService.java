package com.example.reportingservice.service;

import com.example.reportingservice.model.Report;
import com.example.reportingservice.repository.ReportRepository;
import com.example.reportingservice.util.ReportContentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReportService {


    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportContentGenerator reportContentGenerator = new ReportContentGenerator();

    public Report generateProjectsReport(String type, String parameters, String token) {
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateProjectReportContent(token)); // Genera il contenuto JSON
        } catch (Exception e) {
            e.printStackTrace();
            report.setContent("No project found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No project found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report generateProjectReportByProjectId(String type, String parameters, UUID projectId, String token) {
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateProjectReportContentByProjectId(projectId, token)); // Genera il contenuto JSON
        } catch (Exception e) {
            // Gestisci l'eccezione appropriata qui
            e.printStackTrace();
            report.setContent("No project found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No project found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report generateEmployeesReport(String type, String parameters, String token) {
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateEmployeeReportContent(token)); // Genera il contenuto JSON
        } catch (Exception e) {
            // Gestisci l'eccezione appropriata qui
            e.printStackTrace();
            report.setContent("No Employees found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No Employees found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report generateEmployeeReportByEmployeeId(String type, String parameters, UUID employeeId, String token) {
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateEmployeeReportContentByEmployeeId(employeeId, token)); // Genera il contenuto JSON
        } catch (Exception e) {
            // Gestisci l'eccezione appropriata qui
            e.printStackTrace();
            report.setContent("No Employee found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No Employee found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report generateTasksReport(String type, String parameters, String token){
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateTasksReportContent(token)); // Genera il contenuto JSON
        } catch (Exception e) {
            // Gestisci l'eccezione appropriata qui
            e.printStackTrace();
            report.setContent("No Tasks found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No Tasks found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report generateTaskReportByTaskId(String type, String parameters, UUID taskId, String token){
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateTaskReportContentByTaskId(taskId, token)); // Genera il contenuto JSON
        } catch (Exception e) {
            // Gestisci l'eccezione appropriata qui
            e.printStackTrace();
            report.setContent("No Task found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No Task found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report generateDepartmentsReport(String type, String parameters, String token) {
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateDepartmentsReportContent(token)); // Genera il contenuto JSON
        } catch (Exception e) {
            // Gestisci l'eccezione appropriata qui
            e.printStackTrace();
            report.setContent("No Departments found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No Departments found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report generateDepartmentReportByDepartmentId(String type, String parameters, UUID id, String token) {
        Report report = new Report();
        report.setId(UUID.randomUUID().toString());
        report.setType(type);
        report.setParameters(parameters);
        try {
            report.setContent(reportContentGenerator.generateDepartmentReportContentByDepartmentId(id, token)); // Genera il contenuto JSON
        } catch (Exception e) {
            // Gestisci l'eccezione appropriata qui
            e.printStackTrace();
            report.setContent("No Department found");
        }
        report.setGeneratedAt(LocalDateTime.now());
        if(!report.getContent().equalsIgnoreCase("No Department found")) {
            return reportRepository.save(report);
        }else{
            return report;
        }
    }

    public Report getReportById(String id) {
        return reportRepository.findById(id).orElse(null);
    }

    public void deleteReport(String id) {
        reportRepository.deleteById(id);
    }

}
