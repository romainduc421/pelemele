package com.example.pelemele;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;


public class TacheLongue extends AsyncTask <Integer, Integer, Integer>{

    private final Activity y;
    private final ProgressBar z;
    private TextView tv;
    private WeakReference<AppCompatActivity> myActivity;

    public TacheLongue(AppCompatActivity a){
        y=a;
        myActivity = new WeakReference<>(a);
        z = a.findViewById(R.id.progressBar);
        tv = a.findViewById(R.id.textViewEvo);
    }

    public TacheLongue(Activity x){
        y = x;
        z = y.findViewById(R.id.progressBar);
    }
    @Override
    protected Integer doInBackground(Integer... integers) {
        for(int k=1; k<integers[0]; k++){
            try{
                Thread.sleep(80);
                this.publishProgress(k*100/integers[0]);    //publie le progrès en pourcentage
            }catch(InterruptedException e){
                Log.e("tagLongtask", "sleep interruped");
            }

        }
        return new Integer(0);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        this.z.setProgress(0);
        z.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Integer res){
        super.onPostExecute(res);
        z.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        z.setProgress(values[0]);
        tv.setText(values[0] +"%");
    }
}
