package edu.depaul.assignment2_tushar_kumar;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Serializable  {
    private String noteTitle;
    private String noteContent;
    private Date date;

    Note(String title, String content){
        this.noteTitle = title;
        this.noteContent = content;
        this.date = new Date();
    }

    public void setDate(long timeMilliSeconds){
        this.date = new Date(timeMilliSeconds);
    }

    public String getTitle(){
        return noteTitle;
    }

    public String getContent(){
        return noteContent;
    }

    public Date getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "\n" + noteTitle + " | " + noteContent + " | " + date;
    }
}
