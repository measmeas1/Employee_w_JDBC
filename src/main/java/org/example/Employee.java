package org.example;

public class Employee {
    private String id;
    private String name;
    private String jobTitle;
    private String department;
    private String phoneNumber;
    private String email;
    private String employeeType;
    private double salary;
    private String  joinDate;
    private String sex;

    public Employee(String id, String name, String sex, String jobTitle, String department, String phoneNumber, String email, String employeeType, double salary, String joinDate) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employeeType = employeeType;
        this.salary = salary;
        this.joinDate = joinDate;
        this.sex = sex;
    }


    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getEmployeeType(){
        return employeeType;
    }
    public void setType(String employeeType){
        this.employeeType = employeeType;
    }

    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public String getJoinDate(){
        return joinDate;
    }
    public void setJoinDate(String joinDate){
        this.joinDate = joinDate;
    }


}