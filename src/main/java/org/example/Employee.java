package org.example;

public class Employee {
    private int id;
    private String name;
    private String jobTitle;
    private String department;
    private int phoneNumber;
    private String email;
    private String type;
    private double salary;
    private int joinDate;
    private String sex;

    public Employee(int id, String name, String sex, String jobTitle, String department, int phoneNumber, String email, String type, double salary, int joinDate) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
        this.salary = salary;
        this.joinDate = joinDate;
        this.sex = sex;
    }


    public int getId() {
        return id;
    }
    public void setId(int id){
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

    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public int getJoinDate(){
        return joinDate;
    }
    public void setJoinDate(int joinDate){
        this.joinDate = joinDate;
    }


}