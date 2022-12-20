package org.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.models.FileManage;
import org.models.Note;

import java.io.*;

public class Controll {

    private final String CREATION = "Creation.fxml";
    private final String LOGIN = "Login.fxml";
    private final String NOTE = "Note.fxml";
    private FileManage manage = new FileManage();

    //user
    @FXML
    private TextField userName;
    @FXML
    private TextField email;
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


    private void pageSCene(String pageScene, ActionEvent event) throws IOException {
         Parent root;
         Stage stage;
         Scene scene;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pageScene));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void login(ActionEvent event) throws IOException {
        manage.setUser(textUser.getText(), textPassword.getText());
        if (manage.checkPass(manage.getUser().getName(), manage.getUser().getPassword()) == true) {
            activeUser(textUser.getText());
            pageSCene(NOTE, event);
        } else {
            pageSCene(LOGIN, event);
        }
    }

    public void creator(ActionEvent event)throws IOException{
        pageSCene(CREATION,event);
    }

    public void creatUser(ActionEvent event) throws IOException {
        manage.setUser(userName.getText(), password.getText());
        manage.createUser();
        activeUser(userName.getText());
        pageSCene(NOTE, event);
    }

    public void save(ActionEvent event) throws IOException {
        manage.setNote(textTittle.getText(), textArea.getText());
        manage.createNotes(getActiveUser());
        pageSCene(NOTE, event);
    }

    private void activeUser(String user) throws IOException {
        try {

            File path = new File("Notes\\active.txt");

            FileWriter writer = new FileWriter(path);
            String details = "Active User : " + user;
            writer.write(details);
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String getActiveUser() throws IOException {
        String activeUser = "";
        try {
            BufferedReader br
                    = new BufferedReader(new FileReader("Notes\\active.txt"));
            while ((activeUser = br.readLine().substring(14)) != null) {
                System.out.println(activeUser);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return activeUser;
    }






}
