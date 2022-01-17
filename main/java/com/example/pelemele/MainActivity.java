package com.example.pelemele;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    static int PHOTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bonjour = findViewById(R.id.button_bonjour);
        Log.i("MainActivity","une_info");
        bonjour.setOnClickListener((v) ->{
            Toast.makeText(MainActivity.this, "Bonjour", Toast.LENGTH_SHORT).show() ;
        });

        Button chrono = (Button) findViewById(R.id.button_chrono);
        chrono.setOnClickListener((v) ->{
            //Intent avec activité actuelle et activité de destination
            Intent ic = new Intent(MainActivity.this,ChronometreActivity.class);
            //puis on lance l'activité
            startActivity(ic);
        });

        Button pic = (Button) findViewById(R.id.button_pic);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i,PHOTO);
                }
            }
        });

        Button see_pic = (Button) findViewById(R.id.consult_pic);
        see_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(MainActivity.this, Photo.class);
                startActivity(ic);
            }
        });

        Button long_task = (Button) findViewById(R.id.longue_tache);
        long_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(MainActivity.this, LongActivity.class);
                startActivity(ic);
            }
        });

        Button weather = (Button) findViewById(R.id.weatherReport);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(MainActivity.this, MeteoActivity.class);
                startActivity(ic);
            }
        });

        Button contacts = (Button) findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(ic);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Toast.makeText(MainActivity.this, "pic was taken. Height : " + imageBitmap.getHeight()+" Width : "+imageBitmap.getWidth(), Toast.LENGTH_SHORT).show();
            FileOutputStream fos = null;
            try {
                fos = openFileOutput("image.data", MODE_PRIVATE);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException excp) {
                excp.printStackTrace();
            } catch (IOException excp) {
                System.err.println(excp.getLocalizedMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.quitter){
            finish();
            return true;
        }
        return false;
    }
}