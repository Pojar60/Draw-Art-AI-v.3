package com.example.drowartaiv3;

public class DataClass {
    private String imageUrl, caption;
    private boolean isNew;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public DataClass(){

    }

    public DataClass(String imageUrl, String caption, boolean isNew) {
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.isNew = isNew;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
