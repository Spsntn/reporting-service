package com.example.reportingservice.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Task
 */

public class Task   {
  private UUID id = null;

  private String name = null;

  private String description = null;

  private String status = null;

  private UUID projectId = null;

  private UUID employeeId = null;

  public Task id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/

    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Task name(String name) {
    this.name = name;
    return this;
  }


    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Task description(String description) {
    this.description = description;
    return this;
  }


    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Task status(String status) {
    this.status = status;
    return this;
  }


    public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Task projectId(UUID projectId) {
    this.projectId = projectId;
    return this;
  }


    public UUID getProjectId() {
    return projectId;
  }

  public void setProjectId(UUID projectId) {
    this.projectId = projectId;
  }

  public Task employeeId(UUID employeeId) {
    this.employeeId = employeeId;
    return this;
  }


    public UUID getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(UUID employeeId) {
    this.employeeId = employeeId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return Objects.equals(this.id, task.id) &&
        Objects.equals(this.name, task.name) &&
        Objects.equals(this.description, task.description) &&
        Objects.equals(this.status, task.status) &&
        Objects.equals(this.projectId, task.projectId) &&
        Objects.equals(this.employeeId, task.employeeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, status, projectId, employeeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Task {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    employeeId: ").append(toIndentedString(employeeId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
