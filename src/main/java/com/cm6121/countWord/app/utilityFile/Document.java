package com.cm6121.countWord.app.utilityFile;

public class Document {

    private String title;
    private String text;
    private String dateOfCreation;

    public void setText(String text) {
        this.text = text;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getTitle() {
        return title;
    }
}

