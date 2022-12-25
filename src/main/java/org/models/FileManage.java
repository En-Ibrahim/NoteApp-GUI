package org.models;

import org.main.Controll;

import java.io.*;
import java.util.Scanner;

public class FileManage  {

    private User user= new User();;
    private Note note=new Note(user);

    public void setUser(String name,String pass){
        this.user.setName(name);
        this.user.setPassword(pass);

    }

    public void setNote(String tittle,String body){
        this.note.setTittle(tittle);
        this.note.setBody(body);
    }

    public User getUser(){
        return this.user;
    }
    public Note getNote(){
        return this.note;
    }

    public void createUser(){
        getUser().getName();
        getUser().getPassword();
        try{
            File path= new File("Notes");
            path.mkdirs();

            File path1= new File("Notes\\"+getUser().getName());
            path1.mkdirs();

            File file= new File("Notes\\"+getUser().getName()+"\\details.txt");

            FileWriter writer= new FileWriter(file);

            String details= getUser().toString();
            writer.write(details);
            writer.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void createNotes(String activeUser) {

        try {
            File path= new File("Notes\\" + activeUser +"\\Note");
            path.mkdirs();

            File file = new File("Notes\\" + activeUser +"\\Note\\"+ getNote().getTittle()+".txt" );

            FileWriter writer = new FileWriter(file);

            String setDetails = "Tittle : " + getNote().getTittle() + "\nBody \n" + getNote().getBody();
            writer.write(setDetails);
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public String searchUser(String nameFolder) throws IOException {
        File path = new File("Notes");
        String[] childern = path.list();
        if (childern == null) {
            System.out.println("There 's not direct folder");
        } else {
            for (int i = 0; i < childern.length; i++) {
                if (childern[i].equals(nameFolder)) {
                    return childern[i];
                }
            }
        }
        return null;
    }

    public boolean checkPass(String u, String pass) throws IOException {

        try {
            if (searchUser(u) != null) {
                Scanner word = new Scanner(new File("Notes\\" + u + "\\details.txt"));
                while (word.hasNext()) {
                    String str = word.nextLine().substring(11);
                    if (str.indexOf(pass) != -1) {
                        if (pass.compareTo(str) == 0) {
                            return true;
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    //UpDates

    public String[] getAllNotes(){
        File directoryPath = new File("Notes\\"+getUser().getName()+"\\Note");

        String contents[] = directoryPath.list();
        return contents;
    }

    public String EditNote(String note){
        String st = "";
        try {
            File file = new File("Notes\\" + getUser().getName() +"\\Note\\"+note);
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
