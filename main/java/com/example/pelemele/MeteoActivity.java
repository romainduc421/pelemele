package com.example.pelemele;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.json.JSONObject;

public class MeteoActivity extends AppCompatActivity {

    private static Location loc = null;
    private final String apid = BuildConfig.API_KEY;

    private TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);

        temp = (TextView) findViewById(R.id.textView2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.ACCESS_FINE_LOCATION},48);
        }
        final LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        ImageButton meteo = (ImageButton) findViewById(R.id.meteo);

        meteo.setOnClickListener(click-> {

            MeteoActivity.loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(loc != null) {
                double latit = loc.getLatitude(), longit = loc.getLongitude();
                Toast.makeText(MeteoActivity.this, "latitude : " + latit + " \nlongitude : " + longit, Toast.LENGTH_SHORT).show();
                new TacheMeteo(this).execute("https://api.openweathermap.org/data/2.5/weather?lat="+latit+"&lon="+longit+"&appid="+apid);
                //new TacheMeteo(this).execute("https://api.openweathermap.org/data/2.5/weather?zip=54000,fr&appid="+apid);
            }
            else
                Toast.makeText(MeteoActivity.this, "Please activate the location-provider",Toast.LENGTH_SHORT).show();

        });
    }

    public TextView getTemp() {
        return temp;
    }

}
