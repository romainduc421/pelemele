package com.example.pelemele;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ImageButton displayPic = (ImageButton) findViewById(R.id.galerie);
        displayPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = null;
                try{
                    FileInputStream fis = openFileInput("image.data");
                    imageView = (ImageView) findViewById(R.id.galerie);
                    Bitmap bmp = BitmapFactory.decodeStream(fis);
                    imageView.setImageBitmap(bmp);
                }catch(FileNotFoundException excp){
                    System.err.println(excp.getLocalizedMessage());
                }
            }
        });
    }

}
