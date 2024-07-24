package com.example.reportingservice.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DepartmentTest {

    @Test
    public void testDepartmentConstructorAndGettersSetters() {
        UUID id = UUID.randomUUID();
        String name = "Engineering";

        Department department = new Department();
        department.setId(id);
        department.setName(name);

        assertEquals(id, department.getId());
        assertEquals(name, department.getName());
    }

    @Test
    public void testEqualsAndHashCode() {
        Department department1 = new Department();
        department1.setId(UUID.randomUUID());
        department1.setName("Engineering");

        Department department2 = new Department();
        department2.setId(department1.getId());
        department2.setName(department1.getName());

        // Test equality and hash code consistency
        assertEquals(department1, department2);
        assertEquals(department1.hashCode(), department2.hashCode());

        // Change name and check inequality
        department2.setName("Marketing");
        assertNotEquals(department1, department2);
        assertNotEquals(department1.hashCode(), department2.hashCode());
    }

    @Test
    public void testToString() {
        Department department = new Department();
        department.setId(UUID.randomUUID());
        department.setName("Engineering");

        String expectedToString = "class Department {\n" +
                "    id: " + department.getId() + "\n" +
                "    name: " + department.getName() + "\n" +
                "}";

        assertEquals(expectedToString, department.toString());
    }
}
