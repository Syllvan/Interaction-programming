package com.example.johan.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create layout
        GridLayout.Spec row1 = GridLayout.spec(0);
        GridLayout.Spec row2 = GridLayout.spec(1);
        GridLayout.Spec row3 = GridLayout.spec(2);
        GridLayout.Spec row4 = GridLayout.spec(3);

        GridLayout.Spec col1 = GridLayout.spec(0);
        GridLayout.Spec col2 = GridLayout.spec(1);

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(2);
        grid.setRowCount(4);
        grid.setUseDefaultMargins(true);

        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);

        //Create TextViews
        TextView nameText = new TextView(this);
        TextView passText = new TextView(this);
        TextView emailText = new TextView(this);
        TextView ageText = new TextView(this);

        nameText.setText("Name");
        passText.setText("Password");
        emailText.setText("Email");
        ageText.setText("Age");

        //Create EditTexts
        EditText name = new EditText(this);
        EditText password = new EditText(this);
        EditText email = new EditText(this);

        name.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        name.setText("Johan");
        name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        password.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        password.setText("Password");
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        email.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        email.setText("Johan@gmail.com");
        email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        //Create seekbar
        SeekBar seek = new SeekBar(this);

        //Create spec
        GridLayout.LayoutParams first = new GridLayout.LayoutParams(row1,col1);
        first.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams second = new GridLayout.LayoutParams(row1,col2);
        second.width = GridLayout.LayoutParams.MATCH_PARENT;

        GridLayout.LayoutParams third = new GridLayout.LayoutParams(row2,col1);
        third.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams fourth = new GridLayout.LayoutParams(row2,col2);
        fourth.width = GridLayout.LayoutParams.MATCH_PARENT;

        GridLayout.LayoutParams fifth = new GridLayout.LayoutParams(row3,col1);
        fifth.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams sixth = new GridLayout.LayoutParams(row3,col2);
        sixth.width = GridLayout.LayoutParams.MATCH_PARENT;

        GridLayout.LayoutParams seventh = new GridLayout.LayoutParams(row4,col1);
        seventh.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams eighth = new GridLayout.LayoutParams(row4,col2);
        eighth.width = GridLayout.LayoutParams.MATCH_PARENT;

        //Add components to layout

        grid.addView(nameText,first);
        grid.addView(name,second);
        grid.addView(passText,third);
        grid.addView(password,fourth);
        grid.addView(emailText,fifth);
        grid.addView(email,sixth);
        grid.addView(ageText, seventh);
        grid.addView(seek,eighth);

        linear.addView(grid);

        setContentView(linear);
    }
}
