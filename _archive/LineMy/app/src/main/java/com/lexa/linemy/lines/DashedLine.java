package com.lexa.linemy.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;


/**
 * Created by Lexa on 07.06.2016.
 */
public class DashedLine extends Line {

    private float x;
    private float y;
    private float x1;
    private float y1;
    private Paint paintLine;


    public DashedLine(Context context) {
        super(context);
        paintLine = super.paintLine;
        paintLine.setPathEffect(new DashPathEffect(new float[] { 18, 6, 1 }, 0));
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

        path.moveTo(x, y);
        path.lineTo(x1, y1);
        canvas.drawPath(path, paintLine);
    }

    public void moveMyLine(float x2, float y2,Canvas canvas){
        float mx1 = x2-x1;
        float my1 = y2-y1;
        this.x = x+mx1;
        this.y = y+my1;
        this.x1 = x2;
        this.y1 = y2;

        Path path = new Path();

        path.moveTo(x, y);
        path.lineTo(x1, y1);
        canvas.drawPath(path, paintLine);
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
