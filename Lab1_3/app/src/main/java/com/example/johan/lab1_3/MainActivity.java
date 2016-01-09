package com.example.johan.lab1_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout row1 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout row2 = new LinearLayout(this);
        row2.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout row3 = new LinearLayout(this);
        row3.setOrientation(LinearLayout.HORIZONTAL);

        //Create TextView
        TextView text1 = new TextView(this);
        text1.setText("Hur trivs du på LiU?");
        text1.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView text2 = new TextView(this);
        text2.setText("Läser du på LiTH?");
        text2.setGravity(Gravity.CENTER_HORIZONTAL);

        //Create checkboxes
        CheckBox box1 = new CheckBox(this);
        box1.setText("Bra");

        CheckBox box2 = new CheckBox(this);
        box2.setText("Mycket bra");

        CheckBox box3 = new CheckBox(this);
        box3.setText("Jättebra");
        box3.setChecked(true);

        CheckBox box4 = new CheckBox(this);
        box4.setText("Ja");
        box4.setChecked(true);

        CheckBox box5 = new CheckBox(this);
        box5.setText("Nej");

        CheckBox box6 = new CheckBox(this);
        box6.setText("Ja");
        box6.setChecked(true);

        CheckBox box7 = new CheckBox(this);
        box7.setText("Nej");

        //Create Image
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.liu);

        //Create button
        Button button = new Button(this);
        button.setText("SKICKA IN");

        //Add boxes to their layouts
        row1.addView(box1);
        row1.addView(box2);
        row1.addView(box3);

        row2.addView(box4);
        row2.addView(box5);

        row3.addView(box6);
        row3.addView(box7);

        //Add components to main layout
        linearLayout.addView(text1);
        linearLayout.addView(row1);
        linearLayout.addView(text2);
        linearLayout.addView(row2);
        linearLayout.addView(image);
        linearLayout.addView(row3);
        linearLayout.addView(button);

        setContentView(linearLayout);
    }
}
