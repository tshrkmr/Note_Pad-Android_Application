package edu.depaul.assignment2_tushar_kumar;

import java.io.Serializable;
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

//    public void setNoteTitle(String title){
//        this.noteTitle = title;
//    }
//
//    public void setNoteContent(String content){
//        this.noteContent = content;
//    }

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
