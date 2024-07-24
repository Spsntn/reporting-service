package com.example.reportingservice.dto;

import jakarta.validation.constraints.NotBlank;

public class GenerateReportRequest {

    @NotBlank
    private String type;

    @NotBlank
    private String parameters;



    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }


}

