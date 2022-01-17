package com.example.pelemele;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TacheMeteo extends AsyncTask<String,Void, JSONObject> {

    private static final String TAG = "Weather Task";
    private WeakReference<AppCompatActivity> myActivity;
    private Bitmap openWeatherImage;

    public TacheMeteo(AppCompatActivity a){
        this.myActivity = new WeakReference<>(a);
    }

    private JSONObject readStream(InputStream inputStream) throws IOException, JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1000);
        for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
            stringBuilder.append(line);
        }
        inputStream.close();
        Log.d(TAG, stringBuilder.toString());
        return new JSONObject(stringBuilder.toString());
    }

    @Override
    protected JSONObject doInBackground(String... p){
        URL url = null;
        HttpURLConnection urlConnection = null;
        JSONObject res = null;
        try{
            url = new URL(p[0]);
            //Log.d(TAG, "url: "+url );
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            res = readStream(in);
        }catch(IOException | JSONException e){
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
        try {
            JSONArray desc = res.getJSONArray("weather");
            String img = desc.getJSONObject(0).get("icon")+"";
            openWeatherImage = null;
            try {
                URL urlPic = new URL("http://openweathermap.org/img/wn/"+img+"@2x.png");
                urlPic.openConnection();
                InputStream iS = urlPic.openConnection().getInputStream();
                openWeatherImage = BitmapFactory.decodeStream(iS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected void onPostExecute(JSONObject res){
        if(res != null) {
            try {
                JSONObject mainJ = res.getJSONObject("main");
                double temp = mainJ.getDouble("temp") - 273.15;
                int pres = mainJ.getInt("pressure"), hum = mainJ.getInt("humidity");
                JSONObject desc = (JSONObject) res.getJSONArray("weather").get(0);
                //to complete
                AppCompatActivity aca = this.myActivity.get();
                ((TextView)aca.findViewById(R.id.textView2)).setText("Temperature : "+(int)temp+" °");
                ((TextView)aca.findViewById(R.id.textView3)).setText("Description : "+desc.getString("main"));
                ((TextView)aca.findViewById(R.id.textView4)).setText("Ciel : "+desc.getString("description"));
                ((TextView)aca.findViewById(R.id.textView5)).setText("City : "+ res.getString("name"));
                ((ImageView)aca.findViewById(R.id.picW)).setImageBitmap(openWeatherImage);
                ((TextView)aca.findViewById(R.id.textView6)).setText("Pressure : "+pres+" hPa");
                ((TextView)aca.findViewById(R.id.textView7)).setText("Humidity : "+hum+" %");


            } catch (JSONException e) {
                Log.e(TAG, "JSON parsing error!",e);
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values){
    }
}
