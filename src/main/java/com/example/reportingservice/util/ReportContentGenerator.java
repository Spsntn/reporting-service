package com.example.reportingservice.util;

import com.example.reportingservice.model.*;
import com.example.reportingservice.reportModel.DepartmentReportContent;
import com.example.reportingservice.reportModel.EmployeeReportContent;
import com.example.reportingservice.reportModel.ProjectReportContent;
import com.example.reportingservice.reportModel.TaskReportContent;
import com.example.reportingservice.service.DepartmentService;
import com.example.reportingservice.service.EmployeeService;
import com.example.reportingservice.service.ProjectService;
import com.example.reportingservice.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReportContentGenerator {

    @Autowired
    private ProjectService projectService = new ProjectService();

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;


    public ReportContentGenerator(){
    }

    @Autowired
    private ObjectMapper objectMapper;

    public String generateProjectReportContent(String token) throws Exception {
        List<ProjectReportContent> projectReportContents = new ArrayList<>();
        List<Project> allProjects = projectService.getAllProjects(token);
        if (allProjects == null) {
            throw new Exception("Lista progetti vuota o nulla");
        }

        // Itera attraverso ogni progetto per creare il report
        for (Project project : allProjects) {
            //Genera il projectReportContent recuperado il contenuto del project
            ProjectReportContent projectReportContent = getProjectContent(project, token);
            // Aggiungi il report content alla lista
            projectReportContents.add(projectReportContent);
        }

        // Serializza l'oggetto ReportContent in una stringa JSON
        return objectMapper.writeValueAsString(projectReportContents);
    }


    public String generateProjectReportContentByProjectId(UUID projectId, String token) throws JsonProcessingException {
        Project project = projectService.getProjectById(projectId, token);
        ProjectReportContent projectReportContent = getProjectContent(project, token);
        System.out.println("Ritorno il Json per il content");
        return objectMapper.writeValueAsString(projectReportContent);
    }

    public String generateEmployeeReportContent(String token) throws Exception {

        List<Employee> employees = employeeService.getAllEmployees(token);
        List<EmployeeReportContent> employeesReportContent = new ArrayList<EmployeeReportContent>();
        for(Employee employee : employees) {
            EmployeeReportContent employeeReportContent = getEmployeeContent(employee, token);
            employeesReportContent.add(employeeReportContent);
        }

        return objectMapper.writeValueAsString(employeesReportContent);
    }

    public String generateEmployeeReportContentByEmployeeId(UUID employeeId, String token) throws Exception {

        Employee employee = employeeService.findEmployeeById(employeeId, token);
        EmployeeReportContent employeeReportContent = getEmployeeContent(employee, token);
        return objectMapper.writeValueAsString(employeeReportContent);
    }

    public String generateTasksReportContent(String token) throws JsonProcessingException {
        List<Task> tasks = taskService.getAlltasks(token);
        List<TaskReportContent> tasksReportContent = new ArrayList<TaskReportContent>();
        for(Task task : tasks) {
            System.out.println(task.toString());
            TaskReportContent taskReportContent = getTaskContent(task, token);
            tasksReportContent.add(taskReportContent);
        }
        return objectMapper.writeValueAsString(tasksReportContent);
    }

    public String generateTaskReportContentByTaskId(UUID TaskId, String token) throws JsonProcessingException {

        Task task = taskService.getTaskById(TaskId, token);
        try {
            TaskReportContent taskReportContent = getTaskContent(task, token);
            return objectMapper.writeValueAsString(taskReportContent);
        }catch(Exception e){
            System.out.println("Task not found");
            return "{\"error\": \"Task not found\"}";
        }
    }

    public String generateDepartmentsReportContent(String token) throws JsonProcessingException {
        List<Department> departments = departmentService.getAllDepartments(token);
        List<DepartmentReportContent> departmentsReportContent = new ArrayList<DepartmentReportContent>();
        for(Department department : departments) {
            DepartmentReportContent departmentReportContent = getDepartmentContent(department, token);
            departmentsReportContent.add(departmentReportContent);
        }
        return objectMapper.writeValueAsString(departmentsReportContent);
    }

    public String generateDepartmentReportContentByDepartmentId(UUID departmentId, String token) throws JsonProcessingException {
            Department department = departmentService.getDepartmentById(departmentId, token);
            DepartmentReportContent departmentReportContent = getDepartmentContent(department, token);
        return objectMapper.writeValueAsString(departmentReportContent);
    }

    public ProjectReportContent getProjectContent(Project project, String token){
        Map<String, String> projectInfo = getProjectInfo(project, token);
        String project_Name = projectInfo.get("projectName");
        String departmentName = projectInfo.get("departmentName");

        // Recupera le task associate a quel progetto
        List<Task> taskOfProject = taskService.getTasksByProjectId(project.getId(), token);
        List<Map<String, String>> tasks = new ArrayList<>();
        if(taskOfProject!=null && !taskOfProject.isEmpty()) {
            for (Task task : taskOfProject) {
                Map<String, String> taskInfo = new HashMap<>();
                taskInfo.put("taskName", task.getName());
                if (task.getEmployeeId() != null) {
                    taskInfo.put("employeeName", employeeService.findEmployeeById(task.getEmployeeId(), token).getFirstName() + " " + employeeService.findEmployeeById(task.getEmployeeId(), token).getLastName());
                } else {
                    taskInfo.put("employeeName", "No employee assigned");
                }
                taskInfo.put("status", task.getStatus());
                tasks.add(taskInfo);
            }
        }else{
            Map<String, String> noTaskFound = new HashMap<>();
            noTaskFound.put("taskInfo", "No tasks found for this project");
            tasks.add(noTaskFound);
        }


        // Crea l'oggetto ReportContent
        ProjectReportContent projectReportContent = new ProjectReportContent();
        projectReportContent.setProjectId(project.getId().toString());
        projectReportContent.setProjectName(project_Name);
        projectReportContent.setDepartmentName(departmentName);
        projectReportContent.setTasks(tasks);
        return projectReportContent;
    }

    public EmployeeReportContent getEmployeeContent(Employee employee, String token) {
        EmployeeReportContent employeeReportContent = new EmployeeReportContent();
        employeeReportContent.setEmployeeId(employee.getId().toString());
        employeeReportContent.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());
        if(employee.getIdDepartment() != null) {
            employeeReportContent.setDepartmentName(departmentService.getDepartmentById(employee.getIdDepartment(), token).getName());
        }else{
            employeeReportContent.setDepartmentName("No department assigned");
        }
       Map<String, String> taskInfo = new HashMap<String, String>();
        try{
            Task task = taskService.getTaskByEmployeeId(employee.getId(), token);
            taskInfo.put("taskName", task.getName());
            taskInfo.put("status", task.getStatus());
            employeeReportContent.setTask(taskInfo);
        }catch(Exception e){
            taskInfo.put("taskInfo", "No task found for this employee");
            employeeReportContent.setTask(taskInfo);
        }
        return employeeReportContent;
    }

    public TaskReportContent getTaskContent(Task task, String token) {
        TaskReportContent taskReportContent = new TaskReportContent();
        taskReportContent.setTaskId(task.getId().toString());
        taskReportContent.setTaskName(task.getName());
        taskReportContent.setProjectName(projectService.getProjectById(task.getProjectId(), token).getName());
        try {
            taskReportContent.setEmployeeName(employeeService.findEmployeeById(task.getEmployeeId(), token).getFirstName() + " " + employeeService.findEmployeeById(task.getEmployeeId(), token).getLastName());
        }catch(Exception e){
            taskReportContent.setEmployeeName("No employee assigned");
        }
        taskReportContent.setDescription(task.getDescription());
        return taskReportContent;
    }

    public DepartmentReportContent getDepartmentContent(Department department, String token){
        DepartmentReportContent departmentReportContent = new DepartmentReportContent();
        departmentReportContent.setDepartmentId(department.getId().toString());
        departmentReportContent.setDepartmentName(department.getName());
        List<Map<String, String>> projects = new ArrayList<Map<String, String>>();
        try {
            List<Project> projectsList = projectService.getProjectByDepartmentId(department.getId(), token);
            for (Project project : projectsList) {
                Map<String, String> projectInfo = new HashMap<String, String>();
                projectInfo.put("projectId", project.getId().toString());
                projectInfo.put("ProjectName", project.getName());
                projects.add(projectInfo);
            }
        }catch(Exception e){
            Map<String, String> projectInfo = new HashMap<String, String>();
            projectInfo.put("Projects", "No projects found for this department");
            projects.add(projectInfo);
        }
        departmentReportContent.setProjects(projects);
        List<Map<String, String>> employees = new ArrayList<>();
        try {
            for (Employee employee : employeeService.getEmployeesByDepartmentId(department.getId(), token)) {
                Map<String, String> employeeInfo = new HashMap<>();
                employeeInfo.put("employeeName", employee.getFirstName() + " " + employee.getLastName());
                try {
                    Task task = taskService.getTaskByEmployeeId(employee.getId(), token);
                    employeeInfo.put("taskProject", projectService.getProjectById(task.getProjectId(), token).getName());
                    employeeInfo.put("taskName", task.getName());
                    employeeInfo.put("status", task.getStatus());
                } catch (Exception e) {
                    employeeInfo.put("status", "No task assigned");
                }
                employees.add(employeeInfo);
            }
        }catch(Exception e){
            Map<String, String> employeeInfo = new HashMap<>();
            employeeInfo.put("employees", "No employees found for this department");
            employees.add(employeeInfo);
        }
        departmentReportContent.setEmployees(employees);
        return departmentReportContent;
    }

    private Map<String, String> getProjectInfo(Project project, String token) {
        return Map.of(
                "projectName", project.getName(),
                "departmentName", departmentService.getDepartmentById(project.getIdDepartment(), token)!=null ? departmentService.getDepartmentById(project.getIdDepartment(), token).getName() : "No Department Assigned"
        );
    }

}