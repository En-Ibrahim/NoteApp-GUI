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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextArea textArea;

    @FXML
    private Label titleLabel;

    private Parent root;
    private Stage stage;
    private Scene scene;

    private String currentNote;
    private FileManage manage= new FileManage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myListView.getItems().addAll(getAllNotes());
        myListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                currentNote = myListView.getSelectionModel().getSelectedItem();
                myLabel.setText(currentNote);
                try {
                    activeNote(currentNote) ;
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });


    }

    public void backManage(ActionEvent event){
    pageSCene("Manage.fxml",event);
    }
    public void editNote(ActionEvent event){
        if(getActiveNote()!=null) {
            pageSCene("EditNote.fxml", event);
        }

    }

    public void save(ActionEvent event){
        textTittle.setText(getActiveNote());
        manage.setNote(textTittle.getText(),textArea.getText());
        EditNotes(getActiveUser());
        pageSCene("Note.fxml",event);
    }



    //---------User
    public String getActiveUser()  {
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

    public String[] getAllNotes(){
        File directoryPath = new File("Notes\\"+getActiveUser()+"\\Note");

        String contents[] = directoryPath.list();
        return contents;
    }

    //--- Note active
    public void activeNote(String note) throws IOException {
        try {

            File path = new File("Notes\\"+getActiveUser()+"\\activeNote.txt");

            FileWriter writer = new FileWriter(path);
            String details = "Active Note : " + getTitl(note);
            writer.write(details);
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public String getActiveNote()  {
        String activeUser = "";
        try {
            BufferedReader br= new BufferedReader(new FileReader("Notes\\"+getActiveUser()+"\\activeNote.txt"));
            while ((activeUser = br.readLine().substring(14)) != null ) {
                System.out.println(activeUser);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return activeUser;
    }
    public static String getTitl(String title){
        String thename="";
        for(int i=0;i<title.indexOf(".txt");i++) {
            char c = title.charAt(i);
            thename += c;
        }
        return thename;
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
    public void EditNotes(String activeUser) {

        try {
            File path= new File("Notes\\" + activeUser +"\\Note");
            path.mkdirs();

            File file = new File("Notes\\" + activeUser +"\\Note\\"+ manage.getNote().getTittle()+".txt");

            FileWriter writer = new FileWriter(file);

            String setDetails = "Tittle : " + manage.getNote().getTittle() + "\nBody \n" + manage.getNote().getBody();
            writer.write(setDetails);
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public String getNote(){
        String st = "";
        try {
            File file = new File("Notes\\" + getActiveUser() +"\\Note\\"+manage.getNote().getTittle()+".txt");
            BufferedReader br= new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                System.out.println(st);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return st;
    }



}

