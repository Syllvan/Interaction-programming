package com.example.johan.lab1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

public class Laboration1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Declare objects
        RelativeLayout layout = new RelativeLayout(this);
        Button theButton = new Button(this);
        EditText textField  = new EditText(this);
        RatingBar rating = new RatingBar(this);
        EditText multiLineTextfield = new EditText(this);

        //Set id
        theButton.setId(10);
        textField.setId(20);
        rating.setId(30);
        multiLineTextfield.setId(40);

        //Set text
        theButton.setText("button");
        textField.setText("text");
        multiLineTextfield.setText("more text");

        textField.setSingleLine();

        //PARAMETERS
        //Layout parameters
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);

        //Button parameters
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);

        //Text field parameters
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textParams.addRule(RelativeLayout.BELOW, theButton.getId());

        //Rating bar parameters
        RelativeLayout.LayoutParams ratingParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ratingParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        ratingParams.addRule(RelativeLayout.BELOW, textField.getId());

        //text field parameters
        RelativeLayout.LayoutParams multiLineParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        multiLineParams.addRule(RelativeLayout.BELOW, rating.getId());
        multiLineParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

        //Add all objects to layout
        layout.addView(theButton, buttonParams);
        layout.addView(textField, textParams);
        layout.addView(rating, ratingParams);
        layout.addView(multiLineTextfield, multiLineParams);

        setContentView(layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laboration1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
