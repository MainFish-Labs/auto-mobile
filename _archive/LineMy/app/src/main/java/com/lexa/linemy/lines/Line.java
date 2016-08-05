package com.lexa.linemy.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * Created by Lexa on 29.05.2016.
 */
public abstract class Line {

    private float x;
    private float y;
    private float x1;
    private float y1;
    Paint paintLine;
    private Path path;

    public Path getPath() {
        return path;
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


    public Line(Context context) {
        initLine();
    }

    private void initLine()
    {
        paintLine = new Paint();
        paintLine.setColor(Color.BLACK);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(3);
        paintLine.setAntiAlias(true);
    }

    public abstract void drawMyLine(float x, float y, float x1, float y1,Canvas canvas);

    public abstract boolean isLine(float x2, float y2);

    public void drawMyLineEd(Path path,Canvas canvas){

    }

    public void moveMyLine(float x2, float y2,Canvas canvas){

    }

}
