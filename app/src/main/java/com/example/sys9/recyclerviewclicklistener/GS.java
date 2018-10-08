package com.example.sys9.recyclerviewclicklistener;

import java.io.Serializable;

class GS implements Serializable{
    private String description;
    private String title;
    private String id;



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl(String vidUrl) {
        this.vidUrl = vidUrl;
    }

    private String imageUrl;
    private String vidUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setid(String id) {
        this.id=id;

    }
}

