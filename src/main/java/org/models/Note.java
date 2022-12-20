package org.models;

import java.io.File;

public class Note  {
    private String tittle;
    private String body;
    private User user;
    private File path;

    public Note(User user) {
        this.user=user;
        this.path= new File("Notes\\"+this.user.getName()+"\\Note");
        path.mkdirs();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Tittle = " + tittle +"\nBody\n" + body ;
    }
}
