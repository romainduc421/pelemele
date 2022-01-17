package com.example.pelemele;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class LongActivity extends AppCompatActivity {

    private AppCompatActivity myActivity;
    private TextInputLayout input;
    private Button confirm;
    private ProgressBar progBar;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long);
        myActivity = this;
        //progBar = new ProgressBar();
        input = (TextInputLayout) findViewById(R.id.prompt);
        confirm = (Button) findViewById(R.id.valider);
        progBar = (ProgressBar) findViewById(R.id.progressBar);
        tv = (TextView) findViewById(R.id.textViewEvo) ;
        progBar.setVisibility(View.INVISIBLE);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int recup = Integer.parseInt(input.getEditText().getText().toString());
                AsyncTask<Integer,Integer,Integer> longTask = new TacheLongue(myActivity);
                longTask.execute(recup);
            }
        });
    }
}
