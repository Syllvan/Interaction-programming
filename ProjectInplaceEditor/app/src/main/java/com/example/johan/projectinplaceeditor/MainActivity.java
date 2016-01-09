package com.example.johan.projectinplaceeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    InplaceEditor editableText1;
    InplaceEditor editableText2;
    InplaceEditor editableText3;
    TextView text1;
    TextView text2;
    TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);

        editableText1 = new InplaceEditor(this);
        editableText1.setText("Editable Headline");
        editableText1.setTextUpdateListener(new TextUpdateListener() {
            @Override
            public void textUpdate() {
                System.out.println("Text has changed!");
            }
        });
        editableText2 = new InplaceEditor(this);
        editableText2.setText("Another Editable Headline");
        editableText2.setTextUpdateListener(new TextUpdateListener() {
            @Override
            public void textUpdate() {
                System.out.println("Text has changed!");
            }
        });
        editableText3 = new InplaceEditor(this);
        editableText3.setText("And another");
        editableText3.setTextUpdateListener(new TextUpdateListener() {
            @Override
            public void textUpdate() {
                System.out.println("Text has changed!");
            }
        });

        text1=new TextView(this);
        text1.setText("The banana is an edible fruit, botanically a berry, produced by several kinds of large herbaceous flowering plants in the genus Musa. In some countries, bananas used for cooking may be called plantains.");
        text2=new TextView(this);
        text2.setText("The fruit is variable in size, color and firmness, but is usually elongated and curved, with soft flesh rich in starch covered with a rind which may be green, yellow, red, purple, or brown when ripe. The fruits grow in clusters hanging from the top of the plant.");
        text3=new TextView(this);
        text3.setText("Worldwide, there is no sharp distinction between bananas and plantains. Especially in the Americas and Europe.");

        l.addView(editableText1);
        l.addView(text1);
        l.addView(editableText2);
        l.addView(text2);
        l.addView(editableText3);
        l.addView(text3);
        l.setOnClickListener(lClick);
        l.setPadding(20, 20, 20, 20);

        setContentView(l);
    }

    View.OnClickListener lClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editableText1.deselect();
            editableText2.deselect();
            editableText3.deselect();
        }
    };
}