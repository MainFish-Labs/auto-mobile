package com.lexa.linemy.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Lexa on 08.06.2016.
 */
public class ArrowArchLineTwo extends Line {


    private float x;
    private float y;
    private float x1;
    private float y1;
    private Paint paintLine;


    public ArrowArchLineTwo(Context context) {
        super(context);
        paintLine = super.paintLine;

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

        Path path = new Path();

        float deltaX = x1-x;
        float deltaY = y1-y;
        float frac = (float) 0.4;
        float point_x_1 = x + (float) ((1 - frac) * deltaX - frac * deltaY);
        float point_y_1 = y + (float) ((1 - frac) * deltaY + frac * deltaX);
        path.moveTo(x, y);
        path.quadTo(point_x_1, point_y_1, x1, y1);
        canvas.drawPath(path,paintLine);

        float deltaX1 =  x1- point_x_1;
        float deltaY1 =   y1- point_y_1;
        float frac1 = (float) 0.15;
        float point_x_3 = point_x_1 + (float) ((1 - frac1) * deltaX1 + frac1 * deltaY1);
        float point_y_3 = point_y_1 + (float) ((1 - frac1) * deltaY1 - frac1 * deltaX1);
        float point_x_4 = point_x_1 + (float) ((1 - frac1) * deltaX1 - frac1 * deltaY1);
        float point_y_4 = point_y_1 + (float) ((1 - frac1) * deltaY1 + frac1 * deltaX1);


        canvas.drawLine(x1, y1,point_x_4, point_y_4, paintLine);
        canvas.drawLine(x1, y1,point_x_3, point_y_3, paintLine);

    }


    public boolean isLine(float x2, float y2){
        if((x2-x)/(x1-x)== (y2-y)/(y1-y)){
            return true;
        }
        else{
            return false;
        }
    }
}
