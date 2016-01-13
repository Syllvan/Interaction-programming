package com.example.johan.projectinplaceeditor;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
/**
 * Created by johan on 2016-01-08.
 */
public class InplaceEditor extends LinearLayout {
    private Context context;
    private EditText input;
    private LinearLayout buttonLine;
    private Button save;
    private Button cancel;
    private String editableText="";
    private TextUpdateListener textListener;
    private Visualization vis;

    //constructor
    public InplaceEditor(Context c){
        super(c);
        context = c;
        vis = new Visualization();

        setup();
    }

    public InplaceEditor(Context c, Visualization visualization) {
        super(c);
        context = c;
        vis = visualization;

        setup();
    }

    private void setup() {
        this.setOrientation(VERTICAL);

        //Editable text
        input = new EditText(context);
        input.setMaxLines(1);
        input.setFocusable(false);
        input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        input.setBackgroundColor(Color.TRANSPARENT);
        input.setOnClickListener(textClick);
        input.setOnFocusChangeListener(focusChangeListener);

        //Buttons which are available when editing
        save = new Button(context);
        save.setText("Save");
        //save.setLayoutParams(new LinearLayout.LayoutParams(10, 100));
        cancel = new Button(context);
        cancel.setText("Cancel");
        buttonLine = new LinearLayout(context);
        buttonLine.setOrientation(HORIZONTAL);
        buttonLine.addView(save, 500, 150);
        buttonLine.addView(cancel, 500, 150);

        this.addView(input);

        //add listeners for when buttons are pressed
        save.setOnClickListener(saveListener);
        cancel.setOnClickListener(cancelListener);
    }

    public void setTextUpdateListener(TextUpdateListener t){
        textListener = t;
    }

    //Clicking the edit text makes it editable.
    private OnClickListener textClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            input.setFocusable(true);
            input.setFocusableInTouchMode(true);
            input.requestFocus();
        }
    };

    // Checks if selected or not (hasFocus)
    private OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                input.setBackgroundColor(Color.parseColor("#FAEBD7"));
                if(vis.hasButtons()) {
                    InplaceEditor.this.addView(buttonLine);
                }
            }
            else{
                input.setBackgroundColor(Color.TRANSPARENT);
                input.setText(editableText);
                input.setFocusable(false);
                if(vis.hasButtons()) {
                    InplaceEditor.this.removeView(buttonLine);
                }
            }
        }
    };

    public void setText(String s){
        input.setText(s);
        editableText=s;
    }

    //Called from main to deselect inplace editors
    public void deselect(){
        input.clearFocus();
    }

    //Sets editableText to new text
    private OnClickListener saveListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            editableText=input.getText().toString();
            input.clearFocus();
            textListener.textUpdate();
        }
    };

    //Sets editableText to its original text
    private OnClickListener cancelListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            input.setText(editableText);
            input.clearFocus();
        }
    };
}