package com.example.demo.DTO;

public class EmployeeDTO {
    private String name;
    private String jobTitle;
    private Long departmentId; 
    private Double salary;

    public EmployeeDTO() {}

    public EmployeeDTO(String name, String jobTitle, Long departmentId, Double salary) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    
}