package com.example.reportingservice.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskTest {

    @Test
    public void testTaskConstructorAndGettersSetters() {
        UUID id = UUID.randomUUID();
        String name = "Task A";
        String description = "Complete task A";
        String status = "InProgress";
        UUID projectId = UUID.randomUUID();
        UUID employeeId = UUID.randomUUID();

        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
        task.setProjectId(projectId);
        task.setEmployeeId(employeeId);

        assertEquals(id, task.getId());
        assertEquals(name, task.getName());
        assertEquals(description, task.getDescription());
        assertEquals(status, task.getStatus());
        assertEquals(projectId, task.getProjectId());
        assertEquals(employeeId, task.getEmployeeId());
    }

    @Test
    public void testEqualsAndHashCode() {
        Task task1 = new Task();
        task1.setId(UUID.randomUUID());
        task1.setName("Task A");
        task1.setDescription("Complete task A");
        task1.setStatus("InProgress");
        task1.setProjectId(UUID.randomUUID());
        task1.setEmployeeId(UUID.randomUUID());

        Task task2 = new Task();
        task2.setId(task1.getId());
        task2.setName(task1.getName());
        task2.setDescription(task1.getDescription());
        task2.setStatus(task1.getStatus());
        task2.setProjectId(task1.getProjectId());
        task2.setEmployeeId(task1.getEmployeeId());

        // Test equality and hash code consistency
        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());

        // Change status and check inequality
        task2.setStatus("Completed");
        assertNotEquals(task1, task2);
        assertNotEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void testToString() {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setName("Task A");
        task.setDescription("Complete task A");
        task.setStatus("InProgress");
        task.setProjectId(UUID.randomUUID());
        task.setEmployeeId(UUID.randomUUID());

        String expectedToString = "class Task {\n" +
                "    id: " + task.getId() + "\n" +
                "    name: " + task.getName() + "\n" +
                "    description: " + task.getDescription() + "\n" +
                "    status: " + task.getStatus() + "\n" +
                "    projectId: " + task.getProjectId() + "\n" +
                "    employeeId: " + task.getEmployeeId() + "\n" +
                "}";

        assertEquals(expectedToString, task.toString());
    }
}
