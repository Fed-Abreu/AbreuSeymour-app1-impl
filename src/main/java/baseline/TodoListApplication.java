package baseline;

/*
 *  UCF COP3330 Summer 2021 baseline.Application Assignment 1 Solution
 *  Copyright 2021 Federico Abreu Seymour
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TodoListApplication extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ToDoList.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("ToDo Application");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
