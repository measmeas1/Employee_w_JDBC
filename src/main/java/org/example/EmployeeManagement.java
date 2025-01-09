package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeeManagement {


    public void start(Stage primaryStage){
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

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(header);
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
}
