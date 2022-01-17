package com.example.pelemele;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptateurContacts extends RecyclerView.Adapter<ContactItem> {

    private Annuaire a;
    private List<Contact> listContacts;
    AdaptateurContacts(Annuaire a){
        this.a=a;
        listContacts = a.getContactList();
    }

    @Override
    @NonNull
    public ContactItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        int layoutIdForList = R.layout.contact_item;

        View view = inflater.inflate(layoutIdForList,parent,shouldAttachToParentImmediately);
        return new ContactItem(view);
    }

    @Override
    public int getItemCount(){
        //Log.d("CONTACT_ADAPTER","getItemCount : "+listContacts.size());
        return a.getContactList().size();
    }

    @Override
    public void onBindViewHolder(@NonNull ContactItem contactItem, int position){
        Contact cont = listContacts.get(position);
        contactItem.getNameTxt().setText(cont.getName());
        contactItem.getPhoneTxt().setText(cont.getPhoneNumber());
    }
}
