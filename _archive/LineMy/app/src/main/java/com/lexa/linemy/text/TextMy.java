package com.lexa.linemy.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Lexa on 08.06.2016.
 */
public class TextMy {

    private float x;
    private float y;
    private float rotate;
    Paint paintLine;
    String text;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public TextMy (Context context, String text, float rotate) {
        this.text = text;
        this.rotate = rotate;
        initText();
    }

    private void initText()
    {
        paintLine = new Paint();
        paintLine.setColor(Color.BLACK);
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setTextSize(45);
        paintLine.setAntiAlias(true);
    }

    public void drawMyLine(float x, float y, Canvas canvas) {

        this.x = x;
        this.y = y;
        Rect rect = new Rect();
        canvas.save();
        canvas.rotate(rotate,  x + rect.exactCenterX(),y + rect.exactCenterY());
        canvas.drawText(text,x, y, paintLine);
        canvas.restore();

    }
}
