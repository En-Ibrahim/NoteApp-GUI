package org.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.models.FileManage;

import java.io.*;

public class Controll {

    private final String CREATION = "Creation.fxml";
    private final String LOGIN = "Login.fxml";
    private final String NOTE = "Note.fxml";
    private final String MANAGE="ManageNote.fxml";
    private final String SECRETNote = "SecretNote.fxml";
    private FileManage manage = new FileManage();

    //user
    @FXML
    private TextField userName;

    @FXML
    private TextField password;
    //note
    @FXML
    private TextField textTittle;
    @FXML
    private TextArea textArea;

    //login
    @FXML
    private TextField textUser;
    @FXML
    private TextField textPassword;
    @FXML
    private Label myLabel;


    public void pageSCene(String pageScene, ActionEvent event) throws IOException {
         Parent root;
         Stage stage;
         Scene scene;
        Pane loader =  FXMLLoader.load(getClass().getResource(pageScene));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

    //login user
    public void buttonLogin(ActionEvent event) throws IOException {
        manage.setUser(textUser.getText(), textPassword.getText());
        try{
        if (manage.checkPass(manage.getUser().getName(), manage.getUser().getPassword()) == true) {
            manage.activeUser(textUser.getText());
            pageSCene(MANAGE, event);
        } else {
            pageSCene(LOGIN, event);

        }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void buttonCreatAccount(ActionEvent event)throws IOException{
        pageSCene(CREATION,event);
    }

    //create user
    public void buttonCreat(ActionEvent event) throws IOException {
        manage.setUser(userName.getText(), password.getText());
        manage.createUser();
        manage.activeUser(userName.getText());
        pageSCene(MANAGE, event);
    }


    //manage note
    public void buttonSaveNote(ActionEvent event) throws IOException {
        manage.setNote(textTittle.getText(), textArea.getText());
        manage.createNotes(manage.getActiveUser());
        pageSCene(NOTE, event);
    }

    public void buttonCreateNote(ActionEvent event)throws IOException{
        pageSCene(NOTE, event);
    }

    public void buttonEditNote(ActionEvent event)throws IOException{
        pageSCene("Edit.fxml",event);

    }

    public void buttouBackLogin(ActionEvent event)throws IOException{
        pageSCene(LOGIN,event);
    }
    public void buttonPageManage(ActionEvent event)throws IOException{
        pageSCene(MANAGE,event);
    }




}
