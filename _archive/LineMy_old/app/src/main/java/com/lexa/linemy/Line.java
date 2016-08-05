package com.lexa.linemy;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Lexa on 29.05.2016.
 */
public class Line extends Button {

    private float x;
    private float y;
    private float x1;
    private float y1;
    private Paint paintLine;

    public Line(Context context) {
        super(context);
        initLine();
    }

    private void initLine()
    {
        paintLine = new Paint();
        paintLine.setColor(Color.RED);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(3);
        paintLine.setAntiAlias(true);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public void drawMyLine(float x, float y, float x1, float y1,Canvas canvas) {

            this.x = x;
            this.y = y;
            this.x1 = x1;
            this.y1 = y1;

            canvas.drawLine(x, y, x1, y1, paintLine);
            float deltaX =   x1-x;
            float deltaY =   y1-y;
            float frac = (float) 0.1;
            float point_x_1 = x + (float) ((1 - frac) * deltaX + frac * deltaY);
            float point_y_1 = y + (float) ((1 - frac) * deltaY - frac * deltaX);
            float point_x_3 = x + (float) ((1 - frac) * deltaX - frac * deltaY);
            float point_y_3 = y + (float) ((1 - frac) * deltaY + frac * deltaX);

            canvas.drawLine(x1, y1,point_x_1, point_y_1, paintLine);
            canvas.drawLine(x1, y1,point_x_3, point_y_3, paintLine);

        }


    public boolean isLine(float x2, float y2){
        if((x2-x)/(x1-x)== (y2-y)/(y1-y)){
            Log.e("dsfdsffd","is");
            return true;
        }
        else{
            return false;
        }
    }

}
