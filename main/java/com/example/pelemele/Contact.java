package com.example.pelemele;

import androidx.annotation.NonNull;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;
    private boolean isSelected;

    public Contact(){

    }
    public Contact(@NonNull int id,@NonNull String name, @NonNull String phoneNumber){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if(!name.trim().equals("")){
            this.name = name;
            return true;
        }
        return false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if(!name.trim().equals("")){
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    public boolean isSelected(){
        return this.isSelected;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }
}
