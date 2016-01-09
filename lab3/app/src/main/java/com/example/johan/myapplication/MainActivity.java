package com.example.johan.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    InteractiveSearcher interactiveSearcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //LinearLayout layout = new LinearLayout(this);
        interactiveSearcher = new InteractiveSearcher(this);

        //layout.addView(interactiveSearcher);
        setContentView(interactiveSearcher);
    }
}
