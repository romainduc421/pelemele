package com.example.pelemele;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ChronometreActivity extends AppCompatActivity {


    private int seconde, minute;
    int heure_deb, min_deb, sec_deb;
    private boolean stop;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometre);

        TextView textV = findViewById(R.id.text);
        //bouton qu'on va utiliser pour demarrer le chrono
        Button lancer = (Button) findViewById(R.id.lancer);
        Button pause = (Button)findViewById(R.id.stop);

        pause.setVisibility(View.INVISIBLE);

        runnable = new Runnable() {
            @Override
            public void run() {
                seconde+=1;
                if(seconde==60){
                    minute+=1;
                    seconde = 0;
                }
                if(!stop){
                    new Handler().postDelayed(runnable, 1000);
                }
                textV.setText(""+minute+" min "+seconde+" sec");
            }
        };

        lancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GregorianCalendar calendarStart = new GregorianCalendar();
                heure_deb = calendarStart.get(Calendar.HOUR_OF_DAY);
                min_deb = calendarStart.get(Calendar.MINUTE);
                sec_deb = calendarStart.get(Calendar.SECOND) ;
                Toast.makeText(ChronometreActivity.this,"heure : "+ heure_deb+ "h " + min_deb  +" min "+ sec_deb+ "s ",Toast.LENGTH_SHORT).show();

                seconde = 0; minute = 0;
                textV.setText("0 min 0 sec");
                new Handler().postDelayed(runnable, 1000);
                stop = false;
                pause.setVisibility(View.VISIBLE);
                lancer.setVisibility(View.INVISIBLE);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GregorianCalendar calendarStop = new GregorianCalendar();
                int heureStop = calendarStop.get(Calendar.HOUR_OF_DAY),
                minStop = calendarStop.get((Calendar.MINUTE)),
                secStop = calendarStop.get(Calendar.SECOND) ;
                Toast.makeText(ChronometreActivity.this," " + calculChrono(heure_deb,heureStop, min_deb,minStop, sec_deb,secStop) + "secondes ecoulees",Toast.LENGTH_SHORT).show();
                stop = true;
                lancer.setVisibility(View.VISIBLE);
                pause.setVisibility(View.INVISIBLE);
            }
        });

    }

    /**
     * Fonction qui permet de gerer le chrono . elle effectue le calcul du temps a partir du lancement du chrono et au moment de l'arret
     * @param hourBegin l'heure ou l'utilisateur a démarré le chrono
     * @param hourEnd l'heure ou l'utilisateur a stoppé le chrono
     * @param minBegin les minutes correspondant au départ du chrono
     * @param minEnd les minutes correspondant au stop du chrono
     * @param secBegin les secondes correspondant au départ du chrono
     * @param secEnd les secondes correspondant au stop du chrono
     * @return le temps qui s'est écoulé (en seconde) entre le top départ et l'arret du chrono
     */
    public int calculChrono(int hourBegin, int hourEnd, int minBegin, int minEnd, int secBegin, int secEnd){
        int res = 0 ;
        res += (hourBegin != hourEnd)?(hourEnd - hourBegin)*Math.pow(60,2):0 ;
        res += (minBegin != minEnd)?(minEnd - minBegin) * 60:0 ;
        res += (secBegin != secEnd)?secEnd - secBegin : 0 ;
        return res ;
    }

}