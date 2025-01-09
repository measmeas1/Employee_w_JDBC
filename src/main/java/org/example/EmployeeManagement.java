package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class EmployeeManagement extends Application {

    private TableView<Employee> table;
    private ObservableList<Employee> employeeData;
    private Label totalEmployeesLabel;
    private Label fullTimeLabel;
    private Label partTimeLabel;

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

        totalEmployeesLabel = new Label("0");
        VBox totalEmployees = createSummaryCard("Total Employees", totalEmployeesLabel, "#f8f9fa");
        fullTimeLabel = new Label("0");
        VBox fullTime = createSummaryCard("Full-time", fullTimeLabel, "#e8f5e9");
        partTimeLabel = new Label("0");
        VBox partTime = createSummaryCard("Part-time", partTimeLabel, "#fff9c4");

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

        ComboBox<String> comboFill = new ComboBox<>();
        comboFill.getItems().addAll("ID", "Name", "Department");
        comboFill.setValue("ID");
        TextField searchField = new TextField();
        searchField.setPromptText("Search employees...");
        Button searchButton = new Button("🔍");
        ComboBox<String> sortCombo = new ComboBox<>();
        sortCombo.getItems().addAll("Sort by Name", "Sort by Salary");
        Button loadBtn = createButton("Load Data", "#000000", "#FFFFFF");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        actionBox.getChildren().addAll(newBtn, saveBtn, deleteBtn, updateBtn, spacer, comboFill, searchField, searchButton, sortCombo, loadBtn);

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
            String id = empIdField.getText();
            String name = empNameField.getText();
            String sex = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";
            String department = departmentCombo.getValue();
            String jobTitle = jobTitleCombo.getValue();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String empType = typeCombo.getValue();
            String salaryText = salaryField.getText();
            String joinDateText = (joinDatePicker.getValue() != null) ? joinDatePicker.getValue().toString() : "";

            if(id.isEmpty() || name.isEmpty() || sex.isEmpty() || department == null || jobTitle == null || phone.isEmpty() || email.isEmpty() || empType == null || salaryText.isEmpty() || joinDateText.isEmpty()){
                System.out.println("Please fill all the fields!");
                return;
            }

            try {
                double salary = Double.parseDouble(salaryText);

                Employee newEmployee = new Employee(id, name, sex, jobTitle, department, phone, email, empType,salary, joinDateText);

                boolean isSave = Database.saveEmployee(newEmployee);
                if (isSave){
                    employeeData.add(newEmployee);
                    newBtn.fire();
                    updateSummary();
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
                    updateSummary();
                }
            }
        });

        updateBtn.setOnAction(e -> {
            Employee selectedEmployee = table.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                selectedEmployee.setId(emailField.getText());
                selectedEmployee.setName(empNameField.getText());
                selectedEmployee.setSex(rbMale.isSelected() ? "Male" : "Female");
                selectedEmployee.setDepartment(departmentCombo.getValue());
                selectedEmployee.setJobTitle(jobTitleCombo.getValue());
                selectedEmployee.setPhoneNumber(phoneField.getText());
                selectedEmployee.setEmail(emailField.getText());
                selectedEmployee.setType(typeCombo.getValue());
                selectedEmployee.setSalary(Double.parseDouble(salaryField.getText()));
                selectedEmployee.setJoinDate(joinDatePicker.getValue().toString());

                boolean isUpdated = Database.updateEmployee(selectedEmployee);
                if (isUpdated) {
                    table.refresh();
                }
            }
        });

        loadBtn.setOnAction(e -> {
            ObservableList<Employee> employees = Database.loadEmployees();
            employeeData.clear();
            employeeData.addAll(employees);
            updateSummary();
        });

        searchButton.setOnAction(e -> {
            String fillValue = searchField.getText().toLowerCase();
            String fillType = comboFill.getValue();
            table.setItems(employeeData.filtered(emp -> {
                switch (fillType){
                    case "ID":
                        return emp.getId().toLowerCase().contains(fillValue);
                    case "Name":
                        return emp.getName().toLowerCase().contains(fillValue);
                    case "Department":
                        return emp.getDepartment().toLowerCase().contains(fillValue);
                    default:
                        return false;
                }
            }));
        });

        sortCombo.setOnAction(e -> {
            String sortOption = sortCombo.getValue();
            switch (sortOption){
                case "Sort by Name":
                    FXCollections.sort(employeeData, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
                    break;
                case "Sort by Salary":
                    FXCollections.sort(employeeData, (a, b) -> Double.compare(a.getSalary(), b.getSalary()));
                    break;
            }
        });

// --- TABLE SECTION ---
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Employee, Integer> empIdColumn = new TableColumn<>("ID");
        empIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        empIdColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> empNameColumn = new TableColumn<>("Name");
        empNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        empNameColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> sexColumn = new TableColumn<>("Sex");
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        sexColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> jobTitleColumn = new TableColumn<>("Job Title");
        jobTitleColumn.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        jobTitleColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> departmentColumn = new TableColumn<>("Department");
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        departmentColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, Integer> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, String> empTypeColumn = new TableColumn<>("Type");
        empTypeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeType"));
        empTypeColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryColumn.setResizable(true); // Make the column resizable

        TableColumn<Employee, Integer> joinDateColumn = new TableColumn<>("Join Date");
        joinDateColumn.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
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

        employeeData = FXCollections.observableArrayList();
        table.setItems(employeeData);

        VBox.setVgrow(table, Priority.ALWAYS); // Ensure the table fills available space
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(header);
        root.getChildren().addAll(summaryBox, formGrid, actionBox, table);

        Scene scene = new Scene(root, 1400, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void updateSummary() {
        long total = employeeData.size();
        long fullTimeCount = employeeData.stream().filter(emp -> "Full-Time".equals(emp.getEmployeeType())).count();
        long partTimeCount = employeeData.stream().filter(emp -> "Part-Time".equals(emp.getEmployeeType())).count();

        totalEmployeesLabel.setText(String.valueOf(total));
        fullTimeLabel.setText(String.valueOf(fullTimeCount));
        partTimeLabel.setText(String.valueOf(partTimeCount));
    }

    private VBox createSummaryCard(String title, Label valueLabel, String bgColor) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);
        box.setStyle(String.format("-fx-background-color: %s; -fx-border-color: lightgray; -fx-border-radius: 5;", bgColor));
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
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
