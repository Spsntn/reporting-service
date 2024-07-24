package com.example.reportingservice.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
;


public class Project   {

  private UUID id = null;

  private String name = null;


  private String description = null;


  private UUID idDepartment = null;

  public Project id(UUID id) {
    this.id = id;
    return this;
  }


    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Project name(String name) {
    this.name = name;
    return this;
  }


    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Project description(String description) {
    this.description = description;
    return this;
  }


    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project idDepartment(UUID idDepartment) {
    this.idDepartment = idDepartment;
    return this;
  }


    public UUID getIdDepartment() {
    return idDepartment;
  }

  public void setIdDepartment(UUID idDepartment) {
    this.idDepartment = idDepartment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Project project = (Project) o;
    return Objects.equals(this.id, project.id) &&
        Objects.equals(this.name, project.name) &&
        Objects.equals(this.description, project.description) &&
        Objects.equals(this.idDepartment, project.idDepartment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, idDepartment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Project {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    idDepartment: ").append(toIndentedString(idDepartment)).append("\n");
    sb.append("}");
    return sb.toString();
  }


  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
