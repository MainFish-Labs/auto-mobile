package com.lexa.linemy.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;


/**
 * Created by Lexa on 05.06.2016.
 */
public class DoubleLine extends Line {

    private float x;
    private float y;
    private float x1;
    private float y1;
    private Paint paintLine;

    public DoubleLine(Context context) {
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

            float deltaX = x1 - x;
            float deltaY = y1 - y;
            float diag = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            canvas.drawLine(x, y, x1, y1, paintLine);
            canvas.drawLine(x - 10 * deltaY / diag, y + 10 * deltaX / diag, x1 - 10 * deltaY / diag, y1 + 10 * deltaX / diag, paintLine);

    }

    public void moveMyLine(float x2, float y2,Canvas canvas){

        float mx1 = x2-x1;
        float my1 = y2-y1;
        this.x = x+mx1;
        this.y = y+my1;
        this.x1 = x2;
        this.y1 = y2;

        float deltaX = x1 - x;
        float deltaY = y1 - y;
        float diag = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        canvas.drawLine(x, y, x1, y1, paintLine);
        canvas.drawLine(x - 10 * deltaY / diag, y + 10 * deltaX / diag, x1 - 10 * deltaY / diag, y1 + 10 * deltaX / diag, paintLine);

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
