package com.example.asaf2work;



import android.graphics.Bitmap;

public class Friend {
    private String phonenumber;
    private String name;
    private Bitmap picture;
    public Friend(String phonenumber, String firstname, Bitmap picture){
        this.setName(firstname);
        this.setPhonenumber(phonenumber);
        this.setPicture(picture);

    }

    public Bitmap getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}