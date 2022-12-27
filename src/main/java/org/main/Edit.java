package org.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.models.FileManage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Edit implements Initializable {


    @FXML
    private Label myLabel= new Label();
    @FXML
    private ListView<String> myListView= new ListView<>();
    @FXML
    private TextField textTittle= new TextField();
    @FXML
    private TextArea textArea= new TextArea();

    @FXML
    private Button bEdit= new Button();

    @FXML
    private Label titleLabel;

    private Parent root;
    private Stage stage;
    private Scene scene;

    private String currentNote;
    private FileManage manage= new FileManage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myListView.getItems().addAll(manage.getAllNotes());
        myListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                currentNote = myListView.getSelectionModel().getSelectedItem();
                myLabel.setText(currentNote);
                try {
                    manage.activeNote(currentNote) ;
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });


    }

    public void buttonBackManage(ActionEvent event){
        pageSCene("ManageNote.fxml",event);
    }
    public void buttonEditNote(ActionEvent event){
        if(!myLabel.getText().equals("Select Note")) {
            pageSCene("EditNote.fxml", event);
        }
        else{
            pageSCene("Edit.fxml", event);
        }
    }
    public void buttonSaveEditNote(ActionEvent event){
        textTittle.setText(manage.getActiveNote());
        manage.setNote(textTittle.getText(),textArea.getText());
        manage.EditNotes(manage.getActiveUser());
        pageSCene("Note.fxml",event);
    }

    public void buttonDelete(ActionEvent event){
        manage.deleteNote(manage.getActiveNote());
        pageSCene("Edit.fxml",event);
    }


    //move between Scene
    public void pageSCene(String pageScene, ActionEvent event)  {
        try{
            Pane loader =  FXMLLoader.load(getClass().getResource(pageScene));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(loader);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }


}

