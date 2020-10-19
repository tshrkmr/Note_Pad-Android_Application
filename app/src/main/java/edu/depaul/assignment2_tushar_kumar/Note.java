package edu.depaul.assignment2_tushar_kumar;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable  {
    private String noteTitle;
    private String noteContent;
    private Date date;

    Note(String title, String content, Date date){
        this.noteTitle = title;
        this.noteContent = content;
        this.date = date;
    }

    public void setNoteTitle(String title){
        this.noteTitle = title;
    }

    public void setNoteContent(String content){
        this.noteContent = content;
    }

    public void setDate(Date date){
        this.date = date;
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
}
