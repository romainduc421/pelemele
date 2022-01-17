package com.example.pelemele;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private static final int CONTACT_REQUEST = 80;
    private ArrayList<Contact> dataset = new ArrayList<>(40);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            lireContacts();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_REQUEST);
        }
    }

    private void lireContacts(){
        Contact contact;
        boolean res;
        ContentResolver cr = getContentResolver();
        Cursor phones = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

        if( (phones!=null ? phones.getCount() : 0) > 0 ) {
            while (phones.moveToNext()) {
                @SuppressLint("Range")
                int hasPhoneNumber = Integer.parseInt(phones.getString( phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {
                    @SuppressLint("Range")
                    String id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
                    @SuppressLint("Range")
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.d("contact", "getContacts: "+ name);
                    contact = new Contact();
                    res = contact.setName(name);
                    if(!res) {
                        Log.e("contact", "name empty");
                    }

                    Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    if (phoneCursor.moveToNext()) {
                        @SuppressLint("Range")
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        res = contact.setPhoneNumber(phoneNumber);
                        if(!res) {
                            Log.e("contact", "phoneNumber empty");
                        }
                        Log.d("contact", "getContacts: "+ phoneNumber);
                    }
                    phoneCursor.close();

                    dataset.add(contact);
                }
            }
        }
        else{
            String err = "No contacts found.";
            Toast.makeText(ContactActivity.this,err, Toast.LENGTH_SHORT).show();
        }

        AdaptateurContacts adaptateur = new AdaptateurContacts(new Annuaire(dataset));
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptateur);
    }
}
