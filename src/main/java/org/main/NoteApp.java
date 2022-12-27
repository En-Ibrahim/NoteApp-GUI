package org.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
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




//        stage.setTitle("TextArea Experiment 1");
//
//        TextArea textArea = new TextArea();
//
//        Button button = new Button("Click to get text");
//        button.setMinWidth(50);
//
//        button.setOnAction(action -> {
//            System.out.println(textArea.getText());
//
//            textArea.setText("Clicked!");
//        });
//
//        VBox vbox = new VBox(textArea, button);
//
//        Scene scene = new Scene(vbox, 200, 100);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
