package com.geckolabs.notes;

public class NoteModel {
    private int id;
//    private String type;
    private String text;

    //constructors
    public NoteModel(int id, String text) {
        this.id = id;
//        this.type = type;
        this.text = text;
    }

    public NoteModel() {
    }

    //toSting is necessary for printing the contents of a class object
    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
//                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    //setters and getters
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

//    public String getType() {
//
//        return type;
//    }
//
//    public void setType(String type) {
//
//        this.type = type;
//    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }
}
