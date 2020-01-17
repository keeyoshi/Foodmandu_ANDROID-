package com.keeyoshi.foodmandu.model;

public class Detail {

    public String name;
    public String location;
    public String image;
    public String itemtype;

    public Detail(String name, String location, String image, String itemtype) {
        this.name = name;
        this.location = location;
        this.image = image;
        this.itemtype = itemtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemType() {
        return itemtype;
    }

    public void setItemType(String itemtype) {
        this.itemtype = itemtype;
    }
}
