package com.lexa.linemy;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 29.05.2016.
 */
public class TestLinePict extends View{

    private Paint paintScreen;
    private PointF startPoint, endPoint;
    public static float mFieldX = 32;
    public static float mFieldY = 60;

    private Canvas  mCanvas;
    private Context context;
    Bitmap mBitmap;
    Line mLine;
    private int isPaint = 0;

    private List<Line> listLine = new ArrayList<>();

    public TestLinePict(Context context)
    {
        super(context);
        this.context = context;
        initScreen();
    }



    public void isPaint(int isPaint){
        this.isPaint = isPaint;
    }


    private void initScreen()
    {
        paintScreen = new Paint();
        paintScreen.setColor(Color.GRAY);
        paintScreen.setStyle(Paint.Style.STROKE);
        paintScreen.setStrokeWidth(1);
        paintScreen.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        mCanvas = canvas;

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float mx = width / mFieldX;
        float my = height / mFieldY;
        for(int i = 1; i<mFieldX; i++){
            canvas.drawLine(mx*i, 0, mx*i, height, paintScreen);}
        for(int i = 1; i<mFieldY; i++){
            canvas.drawLine(0, my * i, width, my * i, paintScreen);
        }

        if (listLine != null) {
                for (Line lists : listLine) {
                    lists.drawMyLine(lists.getX(), lists.getY(), lists.getX1(), lists.getY1(),canvas);
                }
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

            if(listLine.size() !=0){
                for (Line lists : listLine) {
                    lists.isLine(event.getX(), event.getY());
                }
            }

        mLine = new Line(context);


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    startPoint = new PointF(event.getX(), event.getY());
                    endPoint = new PointF(event.getX(), event.getY());
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:

                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    mLine.drawMyLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y,mCanvas);
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:

                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    mLine.drawMyLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, mCanvas);
                    listLine.add(mLine);

                    isPaint =0;
                    invalidate();
                    break;

                default:
                    break;
            }

        return true;
    }

}
