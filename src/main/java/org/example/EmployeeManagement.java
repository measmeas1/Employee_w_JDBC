package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class EmployeeManagement extends Application {

    private TableView<Employee> table;
    private ObservableList<Employee> employeeData;

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
        TextField phoneField = new TextField();
        formGrid.add(phoneField, 9, 0);

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
        HBox empSex = new HBox(10);
        RadioButton rbMale = new RadioButton("Male");
        RadioButton rbFemale = new RadioButton("Female");
        ToggleGroup empSexGroup = new ToggleGroup();
        rbMale.setToggleGroup(empSexGroup);
        rbFemale.setToggleGroup(empSexGroup);
        empSex.getChildren().addAll(rbMale, rbFemale);
        formGrid.add(empSex, 9, 1);

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
        Button newBtn = createButton("New", "lightblue", "#FFFFFF");
        Button saveBtn = createButton("Save", "#4CAF50", "#FFFFFF");
        Button deleteBtn = createButton("Delete", "#F44336", "#FFFFFF");
        Button updateBtn = createButton("Update", "#FF9800", "#FFFFFF");

        TextField searchField = new TextField();
        searchField.setPromptText("Search employees...");
        Button searchButton = new Button("🔍");
        ComboBox<String> sortCombo = new ComboBox<>();
        sortCombo.getItems().addAll("Sort by Name", "Sort by Age", "Sort by Salary");
        Button loadBtn = createButton("Load Data", "#000000", "#FFFFFF");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        actionBox.getChildren().addAll(newBtn, saveBtn, deleteBtn, updateBtn, spacer, searchField, searchButton, sortCombo, loadBtn);

        employeeData = FXCollections.observableArrayList();
        table.setItems(employeeData);

        newBtn.setOnAction(e -> {
            empIdField.clear();
            empNameField.clear();
            empSexGroup.selectToggle(null);
            departmentCombo.setValue(null);
            jobTitleCombo.setValue(null);
            phoneField.clear();
            emailField.clear();
            typeCombo.setValue(null);
            salaryField.clear();
            joinDatePicker.setValue(null);
        });

        saveBtn.setOnAction(e -> {
            String idText = empIdField.getText();
            String name = empNameField.getText();
            String sex = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";
            String department = departmentCombo.getValue();
            String jobTitle = jobTitleCombo.getValue();
            String phoneText = phoneField.getText();
            String email = emailField.getText();
            String empType = typeCombo.getValue();
            String salaryText = salaryField.getText();
            String joinDateText = joinDatePicker.getValue().toString();

            if(idText.isEmpty() || name.isEmpty() || sex.isEmpty() || department.isEmpty() || jobTitle.isEmpty() || phoneText.isEmpty() || email.isEmpty() || empType.isEmpty() || salaryText.isEmpty() || joinDateText.isEmpty()){
                System.out.println("Please fill all the fields!");
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                int phone = Integer.parseInt(phoneText);
                double salary = Double.parseDouble(salaryText);
                int joinDate = Integer.parseInt(joinDateText);

                Employee newEmployee = new Employee(id, name, sex, jobTitle, department, phone, email, empType,salary, joinDate);

                boolean isSave = Database.saveEmployee(newEmployee);
                if (isSave){
                    employeeData.add(newEmployee);
                    newBtn.fire();
                } else {
                    System.out.println("Failed to save employee.");
                }
            } catch (NumberFormatException ex){
                System.out.println("Invalid id, phone, salary or join date format.");
            }
        });

        deleteBtn.setOnAction(e -> {
            Employee selectedEmployee = table.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                boolean isDeleted = Database.deleteEmployee(selectedEmployee.getId());
                if (isDeleted) {
                    employeeData.remove(selectedEmployee);
                }
            }
        });

        deleteBtn.setOnAction(e -> {
            Employee selectedEmployee = table.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                boolean isDeleted = Database.deleteEmployee(selectedEmployee.getId());
                if (isDeleted) {
                    employeeData.remove(selectedEmployee);
                }
            }
        });

        updateBtn.setOnAction(e -> {
            Employee selectedEmployee = table.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                selectedEmployee.setId(Integer.parseInt(emailField.getText()));
                selectedEmployee.setName(empNameField.getText());
                selectedEmployee.setSex(rbMale.isSelected() ? "Male" : "Female");
                selectedEmployee.setDepartment(departmentCombo.getValue());
                selectedEmployee.setJobTitle(jobTitleCombo.getValue());
                selectedEmployee.setPhoneNumber(Integer.parseInt(phoneField.getText()));
                selectedEmployee.setEmail(emailField.getText());
                selectedEmployee.setType(typeCombo.getTypeSelector());
                selectedEmployee.setSalary(Double.parseDouble(salaryField.getText()));
                selectedEmployee.setJoinDate(Integer.parseInt(joinDatePicker.getValue().toString()));

                boolean isUpdated = Database.updateEmployee(selectedEmployee);
                if (isUpdated) {
                    table.refresh();
                }
            }
        });


// --- TABLE SECTION ---
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Employee, Integer> empIdColumn = new TableColumn<>("E.ID");
        empIdColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> empNameColumn = new TableColumn<>("E.Name");
        empNameColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> sexColumn = new TableColumn<>("Sex");
        sexColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> jobTitleColumn = new TableColumn<>("Job Title");
        jobTitleColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> departmentColumn = new TableColumn<>("Department");
        departmentColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, Integer> phoneColumn = new TableColumn<>("Phone.N");
        phoneColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> empTypeColumn = new TableColumn<>("E.Type");
        empTypeColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, Integer> joinDateColumn = new TableColumn<>("Join Date");
        joinDateColumn.setResizable(true); // Make the column resizable

        table.getColumns().addAll(
                empIdColumn,
                empNameColumn,
                sexColumn,
                jobTitleColumn,
                departmentColumn,
                phoneColumn,
                emailColumn,
                empTypeColumn,
                salaryColumn,
                joinDateColumn
        );

        VBox.setVgrow(table, Priority.ALWAYS); // Ensure the table fills available space
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(header);
        root.getChildren().addAll(summaryBox, formGrid,actionBox, table);

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
