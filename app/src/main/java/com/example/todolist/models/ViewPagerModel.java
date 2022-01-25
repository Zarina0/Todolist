package com.example.todolist.models;

public class ViewPagerModel {
    private String title;
    private String description;
    private int image;
    private String btntext;

    public ViewPagerModel(String title, String description, int image, String btntext) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.btntext = btntext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBtntext() {
        return btntext;
    }

    public void setBtntext(String btntext) {
        this.btntext = btntext;
    }
}

