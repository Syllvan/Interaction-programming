package com.example.johan.projectpasswordstrengthmeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PasswordStrengthMeter psm;
    Toast t;
    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        psm = new PasswordStrengthMeter(this);
        // alternatively create a PasswordStrengthMeter with
        // custom PasswordRequirements or StrengthVisualization.
        // See those constructors in PasswordStrengthMeter.java

        t = new Toast(this);
        l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(20, 20, 20, 20);

        TextView username = new TextView(this);
        username.setText("Username");

        TextView password = new TextView(this);
        password.setText("Password");

        EditText user = new EditText(this);

        Button ok = new Button(this);
        ok.setText("Ok");

        l.addView(username);
        l.addView(user);
        l.addView(password);
        l.addView(psm);
        l.addView(ok);

        setContentView(l);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (psm.containsMinimum()) {
                    t.makeText(getApplicationContext(), "Password Accepted", Toast.LENGTH_SHORT).show();
                }

                else {
                    t.makeText(getApplicationContext(), "Password Too Short", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
