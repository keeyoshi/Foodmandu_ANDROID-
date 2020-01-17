package com.keeyoshi.foodmandu.model;

public class Member {

    public String name;
    public String location;
    public String itemtype;
    public String image;

    public Member(String name, String location, String itemtype, String image) {
        this.name = name;
        this.location = location;
        this.itemtype = itemtype;
        this.image = image;
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

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
