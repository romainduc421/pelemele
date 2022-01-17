package com.example.pelemele;

import java.util.ArrayList;
import java.util.List;

public class Annuaire {

    private List<Contact> contactList;

    public Annuaire(ArrayList<Contact> contacts){
        this.contactList = contacts;
    }

    public List<Contact> getContactList() {
        return contactList;
    }
}
