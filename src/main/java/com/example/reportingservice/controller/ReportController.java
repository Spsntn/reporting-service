package com.example.reportingservice.controller;

import com.example.reportingservice.dto.*;
import com.example.reportingservice.model.Report;
import com.example.reportingservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<String> sayHello(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @PostMapping("/project")
    public ResponseEntity<Report> generateProjectReport(@RequestHeader HttpHeaders headers, @RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);

        try {
            Report report = reportService.generateProjectsReport(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/project/{id}")
    public ResponseEntity<Report> generateProjectReport(@RequestHeader HttpHeaders headers,@PathVariable UUID id, @RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);
        try {
            Report report = reportService.generateProjectReportByProjectId(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    id,
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/task")
    public ResponseEntity<Report> generateTasksReport(@RequestHeader HttpHeaders headers, @RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);
        try {
            Report report = reportService.generateTasksReport(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/task/{id}")
    public ResponseEntity<Report> generaTaskReport(@RequestHeader HttpHeaders headers, @PathVariable UUID id, @RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);
        try {
            Report report = reportService.generateTaskReportByTaskId(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    id,
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/department")
    public ResponseEntity<Report> generateDepartmentsReport(@RequestHeader HttpHeaders headers,@RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);;
        try {
            Report report = reportService.generateDepartmentsReport(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/department/{id}")
    public ResponseEntity<Report> generateDepartmentReport(@RequestHeader HttpHeaders headers,@PathVariable UUID id, @RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);;
        try {
            Report report = reportService.generateDepartmentReportByDepartmentId(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    id,
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<Report> generateEmployeesReport(@RequestHeader HttpHeaders headers,@RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);;
        try {
            Report report = reportService.generateEmployeesReport(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/employee/{id}")
    public ResponseEntity<Report> generateEmployeeReportByEmployeeId(@RequestHeader HttpHeaders headers,@PathVariable UUID id, @RequestBody @Valid GenerateReportRequest reportRequest) {
        StringBuilder headersInfo = new StringBuilder();
        String token = headers.get("authorization").get(0).substring(7);;
        try {
            Report report = reportService.generateEmployeeReportByEmployeeId(
                    reportRequest.getType(),
                    reportRequest.getParameters(),
                    id,
                    token
            );
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable String id) {
        Report report = reportService.getReportById(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        reportService.deleteReport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

