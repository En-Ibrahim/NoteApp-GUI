package org.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NoteApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
        Parent root =  FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Note App");
        stage.setScene(scene);
        stage.show();
    }catch (IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
