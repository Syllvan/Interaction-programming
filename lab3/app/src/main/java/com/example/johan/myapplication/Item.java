package com.example.johan.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
/**
 * Created by johan on 2015-12-10.
 */
public class Item extends View {
    String name;

    public Item(Context c, String n){
        super(c);
        name = n;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint items = new Paint(Paint.ANTI_ALIAS_FLAG);
        items.setColor(Color.BLACK);
        items.setTextSize(50);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#fafafa"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        canvas.drawText(name, 30, 70, items);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(500, 100);
    }

}
