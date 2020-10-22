package edu.depaul.assignment2_tushar_kumar;

import java.io.Serializable;

public class Note implements Serializable  {
    private String noteTitle;
    private String noteContent;
    private String date;
    private static final String TAG = "Note";
    Note(String title, String content, String date){
        this.noteTitle = title;
        this.noteContent = content;
        this.date = date;
    }

//    public void setDate(long timeMilliSeconds){
//        this.date = new Date(timeMilliSeconds);
//    }

    public String getTitle(){
        return this.noteTitle;
    }

    public String getContent(){
        return this.noteContent;
    }

    public String getDate(){
        return this.date;
    }

    @Override
    public String toString() {
        return "\n" + noteTitle + " | " + noteContent + " | " + date;
    }
}
