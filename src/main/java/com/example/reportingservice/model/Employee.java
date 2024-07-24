package com.example.reportingservice.model;

import java.util.Objects;
import java.util.UUID;




public class Employee   {

  private UUID id = null;

  private String firstName = null;

  private String lastName = null;

  private String email = null;
  private String password;
  private String username;

  private UUID idDepartment = null;

  public Employee id(UUID id) {
    this.id = id;
    return this;
  }


    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Employee firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }


    public String getFirstName() {
    return firstName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Employee lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }


    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Employee email(String email) {
    this.email = email;
    return this;
  }


    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Employee idDepartment(UUID idDepartment) {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(email, employee.email) && Objects.equals(password, employee.password) && Objects.equals(username, employee.username) && Objects.equals(idDepartment, employee.idDepartment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, password, username, idDepartment);
  }

  @Override
  public String toString() {
    return "Employee{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", username='" + username + '\'' +
            ", idDepartment=" + idDepartment +
            '}';
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
