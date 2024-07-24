package com.example.reportingservice.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectTest {

    @Test
    public void testProjectConstructorAndGettersSetters() {
        UUID id = UUID.randomUUID();
        String name = "Project X";
        String description = "A new project";
        UUID idDepartment = UUID.randomUUID();

        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setDescription(description);
        project.setIdDepartment(idDepartment);

        assertEquals(id, project.getId());
        assertEquals(name, project.getName());
        assertEquals(description, project.getDescription());
        assertEquals(idDepartment, project.getIdDepartment());
    }

    @Test
    public void testEqualsAndHashCode() {
        Project project1 = new Project();
        project1.setId(UUID.randomUUID());
        project1.setName("Project X");
        project1.setDescription("A new project");
        project1.setIdDepartment(UUID.randomUUID());

        Project project2 = new Project();
        project2.setId(project1.getId());
        project2.setName(project1.getName());
        project2.setDescription(project1.getDescription());
        project2.setIdDepartment(project1.getIdDepartment());

        // Test equality and hash code consistency
        assertEquals(project1, project2);
        assertEquals(project1.hashCode(), project2.hashCode());

        // Change name and check inequality
        project2.setName("Project Y");
        assertNotEquals(project1, project2);
        assertNotEquals(project1.hashCode(), project2.hashCode());
    }

    @Test
    public void testToString() {
        Project project = new Project();
        project.setId(UUID.randomUUID());
        project.setName("Project X");
        project.setDescription("A new project");
        project.setIdDepartment(UUID.randomUUID());

        String expectedToString = "class Project {\n" +
                "    id: " + project.getId() + "\n" +
                "    name: " + project.getName() + "\n" +
                "    description: " + project.getDescription() + "\n" +
                "    idDepartment: " + project.getIdDepartment() + "\n" +
                "}";

        assertEquals(expectedToString, project.toString());
    }
}
