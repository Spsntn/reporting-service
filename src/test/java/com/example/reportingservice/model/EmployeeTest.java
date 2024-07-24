package com.example.reportingservice.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EmployeeTest {

    @Test
    public void testEmployeeConstructorAndGettersSetters() {
        UUID id = UUID.randomUUID();
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "password";
        String username = "johndoe";
        UUID departmentId = UUID.randomUUID();

        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setUsername(username);
        employee.setIdDepartment(departmentId);

        assertEquals(id, employee.getId());
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(email, employee.getEmail());
        assertEquals(password, employee.getPassword());
        assertEquals(username, employee.getUsername());
        assertEquals(departmentId, employee.getIdDepartment());
    }

    @Test
    public void testEqualsAndHashCode() {
        Employee employee1 = new Employee();
        employee1.setId(UUID.randomUUID());
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("john.doe@example.com");

        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        employee2.setFirstName(employee1.getFirstName());
        employee2.setLastName(employee1.getLastName());
        employee2.setEmail(employee1.getEmail());

        // Test equality and hash code consistency
        assertEquals(employee1, employee2);
        assertEquals(employee1.hashCode(), employee2.hashCode());

        // Change one field and check inequality
        employee2.setFirstName("Jane");
        assertNotEquals(employee1, employee2);
        assertNotEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    public void testToString() {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");

        String expectedToString = "Employee{" +
                "id=" + employee.getId() +
                ", firstName='" + employee.getFirstName() + '\'' +
                ", lastName='" + employee.getLastName() + '\'' +
                ", email='" + employee.getEmail() + '\'' +
                ", password='null'" + // Password non incluso nello string di toString
                ", username='null'" + // Username non incluso nello string di toString
                ", idDepartment=null" + // IdDepartment non incluso nello string di toString
                '}';

        assertEquals(expectedToString, employee.toString());
    }
}

