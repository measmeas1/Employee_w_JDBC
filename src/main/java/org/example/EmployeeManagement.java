package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EmployeeManagement extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System");

        //Header
        Label header = new Label("Employee Management System");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(Pos.CENTER);
        VBox.setVgrow( header, Priority.NEVER);

        //Top Summary
        HBox summaryBox = new HBox(20);
        summaryBox.setPadding(new Insets(10));
        summaryBox.setAlignment(Pos.CENTER);
        summaryBox.setSpacing(20);
        HBox.setHgrow(summaryBox, Priority.ALWAYS);

        VBox totalEmployees = createSummaryCard("Total Employees", "0", "#f8f9fa");
        VBox fullTime = createSummaryCard("Full-time", "0", "#e8f5e9");
        VBox partTime = createSummaryCard("Part-time", "0", "#fff9c4");

        HBox.setHgrow(totalEmployees, Priority.ALWAYS);
        HBox.setHgrow(fullTime, Priority.ALWAYS);
        HBox.setHgrow(partTime, Priority.ALWAYS);
        summaryBox.getChildren().addAll(totalEmployees, fullTime, partTime);

        // --- FORM SECTION (Single Row) ---
        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(10));
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setStyle("-fx-alignment: center;");
        formGrid.add(new Label("Employee ID:"), 0, 0);
        TextField empIdField = new TextField();
        formGrid.add(empIdField, 1, 0);

        formGrid.add(new Label("Employee Name:"), 2, 0);
        TextField empNameField = new TextField();
        formGrid.add(empNameField, 3, 0);

        formGrid.add(new Label("Department:"), 4, 0);
        ComboBox<String> departmentCombo = new ComboBox<>();
        departmentCombo.getItems().addAll("IT", "HR", "Finance", "Sales & Marketing");
        formGrid.add(departmentCombo, 5, 0);

        formGrid.add(new Label("Job Title:"), 6, 0);
        ComboBox<String> jobTitleCombo = new ComboBox<>();
        jobTitleCombo.setPromptText("Select Department First");
        formGrid.add(jobTitleCombo, 7, 0);

        formGrid.add(new Label("Phone Number:"), 8, 0);
        TextField ageField = new TextField();
        formGrid.add(ageField, 9, 0);

        formGrid.add(new Label("Email:"), 0, 1);
        TextField emailField = new TextField();
        formGrid.add(emailField, 1, 1);

        formGrid.add(new Label("Employee Type:"), 2, 1);
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Part-Time", "Full-Time");
        formGrid.add(typeCombo, 3, 1);

        formGrid.add(new Label("Salary:"), 4, 1);
        TextField salaryField = new TextField();
        formGrid.add(salaryField, 5, 1);

        formGrid.add(new Label("Join Date:"), 6, 1);
        DatePicker joinDatePicker = new DatePicker();
        formGrid.add(joinDatePicker, 7, 1);

        formGrid.add(new Label("Sex:"), 8, 1);
        HBox empTypeBox = new HBox(10);
        RadioButton fullTimeBtn = new RadioButton("Male");
        RadioButton partTimeBtn = new RadioButton("Female");
        ToggleGroup empTypeGroup = new ToggleGroup();
        fullTimeBtn.setToggleGroup(empTypeGroup);
        partTimeBtn.setToggleGroup(empTypeGroup);
        empTypeBox.getChildren().addAll(fullTimeBtn, partTimeBtn);
        formGrid.add(empTypeBox, 9, 1);

        // Add listener to departmentCombo to update job titles
        departmentCombo.setOnAction(event -> {
            jobTitleCombo.getItems().clear(); // Clear previous options
            String selectedDepartment = departmentCombo.getValue();

            if ("IT".equals(selectedDepartment)) {
                jobTitleCombo.getItems().addAll(
                        "Software Engineer",
                        "System Administrator",
                        "Network Engineer",
                        "IT Support"
                );
            } else if ("HR".equals(selectedDepartment)) {
                jobTitleCombo.getItems().addAll(
                        "HR Manager",
                        "Recruitment Specialist",
                        "Payroll Administrator",
                        "Training Coordinator"
                );
            } else if ("Finance".equals(selectedDepartment)) {
                jobTitleCombo.getItems().addAll(
                        "Financial Analyst",
                        "Accountant",
                        "Budget Planner",
                        "Auditor"
                );
            } else if ("Sales & Marketing".equals(selectedDepartment)) {
                jobTitleCombo.getItems().addAll(
                        "Sales Manager",
                        "Marketing Specialist",
                        "Customer Relationship Manager",
                        "Business Development Executive"
                );
            }
        });

        HBox actionBox = new HBox(10);
        actionBox.setPadding(new Insets(10));
        actionBox.setAlignment(Pos.CENTER_LEFT);
        Button newBtn = createButton("New", "#000000", "#FFFFFF");
        Button saveBtn = createButton("Save", "#4CAF50", "#FFFFFF");
        Button deleteBtn = createButton("Delete", "#F44336", "#FFFFFF");
        Button updateBtn = createButton("Update", "#FF9800", "#FFFFFF");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(header);
        root.getChildren().addAll(summaryBox, formGrid);

        Scene scene = new Scene(root, 1400, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSummaryCard(String title, String value, String bgColor) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);
        box.setStyle(String.format("-fx-background-color: %s; -fx-border-color: lightgray; -fx-border-radius: 5;", bgColor));
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        box.getChildren().addAll(titleLabel, valueLabel);
        return box;
    }

    private Button createButton(String text, String bgColor, String textColor) {
        Button button = new Button(text);
        button.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: %s;", bgColor, textColor));
        return button;
    }
}
