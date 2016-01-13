package com.example.johan.projectpasswordstrengthmeter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by johan on 2016-01-02.
 */
public class PasswordStrengthMeter extends LinearLayout{
    private Context context;
    private EditText userInput;
    private LinearLayout textLine;
    private TextView passStrength;
    private TextView strengthText;
    private ProgressBar progress;
    private PasswordRequirements pr;
    private StrengthVisualization sv;

    public PasswordStrengthMeter(Context c){
        super(c);
        context = c;
        pr = new PasswordRequirements();
        sv = new StrengthVisualization();

        setup();
    }

    public PasswordStrengthMeter(Context c, PasswordRequirements inputPR){
        super(c);
        context = c;
        pr = inputPR;
        sv = new StrengthVisualization();

        setup();
    }

    public PasswordStrengthMeter(Context c, StrengthVisualization inputSV){
        super(c);
        context = c;
        pr = new PasswordRequirements();
        sv = inputSV;

        setup();
    }

    public PasswordStrengthMeter(Context c, PasswordRequirements inputPR, StrengthVisualization inputSV){
        super(c);
        context = c;
        pr = inputPR;
        sv = inputSV;

        setup();
    }

    //Seperate all common setup for the constructors.
    private void setup() {
        this.setOrientation(LinearLayout.VERTICAL);

        userInput = new EditText(context);
        userInput.setMaxLines(1);
        userInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        userInput.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        textLine = new LinearLayout(context);
        textLine.setOrientation(HORIZONTAL);
        passStrength = new TextView(context);
        passStrength.setText("Password Strength: ");
        strengthText = new TextView(context);
        strengthText.setText(" Too short");
        strengthText.setTextColor(Color.GRAY);
        strengthText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textLine.addView(passStrength);
        textLine.addView(strengthText);

        progress = new ProgressBar(context,null,android.R.attr.progressBarStyleHorizontal);
        progress.setMax(5);
        progress.setProgress(0);
        progress.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);

        this.addView(userInput);
        if(sv.hasStrengthText()) {
            this.addView(textLine);
        }
        if(sv.hasStrengthBar()) {
            this.addView(progress);
        }

        userInput.addTextChangedListener(watcher);
    }

    public boolean containsMinimum (){
        return pr.containsMinimum(userInput.getText().toString());
    }

    private void setStrengthText(int currentPoints){
        switch(currentPoints){
            case 1:
                strengthText.setText(" Too short");
                strengthText.setTextColor(Color.GRAY);
                break;
            case 2:
                strengthText.setText(" Weak");
                strengthText.setTextColor(Color.RED);
                break;
            case 3:
                strengthText.setText(" Fair");
                strengthText.setTextColor(Color.MAGENTA);
                break;
            case 4:
                strengthText.setText(" Good");
                strengthText.setTextColor(Color.BLUE);
                break;
            case 5:
                strengthText.setText(" Strong");
                strengthText.setTextColor(Color.GREEN);
        }
    }

    private void updateProgressBar(int level){
        progress.setProgress(level);

        switch(level){
            case 1:
                progress.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                break;
            case 2:
                progress.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                break;
            case 3:
                progress.getProgressDrawable().setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN);
                break;
            case 4:
                progress.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                break;
            case 5:
                progress.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        }
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String password=s.toString();
            int level = 0;
            if (!password.equals("")){
                level=pr.passwordStrengthLevel(password);
                if(sv.hasStrengthText()) {
                    setStrengthText(level);
                }
                if(sv.hasStrengthBar()) {
                    updateProgressBar(level);
                }
            }
            else{
                strengthText.setText(" Too short");
                strengthText.setTextColor(Color.GRAY);
                progress.setProgress(0);
                progress.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
