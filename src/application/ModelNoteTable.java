/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author USER
 */
public class ModelNoteTable {
    String id,author,note,date;
//    private final StringProperty col_a = null;
//    private final StringProperty col_b = null;
//    private final StringProperty col_c = null;
   
    public ModelNoteTable(String id,String author,String note,String date){
        this.id = id;
        this.author = author;
        this.note = note;
        this.date = date;
    }
    
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    } 
    public String getNote(){
        return note;
    }
     public void setNote(String note){
        this.note = note;
    }
     
   
    public String getDate(){
        return date;
    }
     public void setDate(String date){
        this.date= date;
    }
      
    
}

