package org.example;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3307/homework_jdbc";
    private static final String User = "root";
    private static final String Password = "";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, Password);
    }

    public static boolean saveEmployee(Employee employee){
        String query = "INSERT INTO Employees_homework2 (emp_id, emp_name, sex, job_title, department, phone, email, employee_type, salary, join_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getSex());
            statement.setString(4, employee.getJobTitle());
            statement.setString(5, employee.getDepartment());
            statement.setInt(6, employee.getPhoneNumber());
            statement.setString(7, employee.getEmail());
            statement.setString(8, employee.getType());
            statement.setDouble(9, employee.getSalary());
            statement.setInt(10, employee.getJoinDate());

            int rowsAffect = statement.executeUpdate();
            return rowsAffect > 0;

        } catch (SQLException e) {
            System.out.println("Error in saveEmployee: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteEmployee (int id){
        String query = "DELETE FROM Employees_homework2 WHERE emp_id = ?";

        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, Integer.parseInt(String.valueOf(id)));

            int rowAffect = statement.executeUpdate();
            return rowAffect > 0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateEmployee(Employee employee){
        String query = "UPDATE Employees_homework2 SET emp_name = ?, sex = ?, job_title = ?, department = ?, phone = ?, email = ?, employee_type = ? , salary = ?, join_date = ? WHERE emp_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSex());
            statement.setString(3, employee.getJobTitle());
            statement.setString(4, employee.getDepartment());
            statement.setInt(5, employee.getPhoneNumber());
            statement.setString(6, employee.getEmail());
            statement.setString(7, employee.getType());
            statement.setDouble(8, employee.getSalary());
            statement.setInt(9, employee.getJoinDate());
            statement.setInt(10, employee.getId());

            int rowAffect = statement.executeUpdate();
            return rowAffect > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //Fetch from database
    public static ObservableList<Employee> loadEmployee(){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        String query = "SELECT * FROM Employees_homework2";

        try(Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getInt("emp_id"),
                        resultSet.getString("emp_name"),
                        resultSet.getString("sex"),
                        resultSet.getString("job_title"),
                        resultSet.getString("department"),
                        resultSet.getInt("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("employee_type"),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("join_date")
                );
                employees.add(employee);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employees;
    }
}
