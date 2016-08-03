package com.mainfish.europrotocola04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by artli on 04.05.2016.
 */
public class DrawView extends View {

    public static final int ARROW = 1;
    public static final int LINE = 2;

    public int mCurrentShape;

    protected Path mPath;
    protected Paint mPaint;
    protected Paint mPaintFinal;
    protected Bitmap mBitmap;
    protected Canvas mCanvas;

    protected ArrayList<Path> paths = new ArrayList<Path>();
    protected ArrayList<Path> undonePaths = new ArrayList<Path>();

    protected ShapeDrawable mDrawable;

    protected boolean isDrawing = false;
    protected boolean isDrawingEnded = false;

    public static final float TOUCH_TOLERANCE = 4;
    public static final float TOUCH_STROKE_WIDTH = 5;

    protected float mStartX;
    protected float mStartY;

    protected float mx;
    protected float my;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    public DrawView (Context context) {

        super(context);
        init();

    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init() {

        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getContext().getResources().getColor(android.R.color.holo_red_light));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(TOUCH_STROKE_WIDTH);

        mPaintFinal = new Paint(Paint.DITHER_FLAG);
        mPaintFinal.setAntiAlias(true);
        mPaintFinal.setDither(true);
        mPaintFinal.setColor(getContext().getResources().getColor(android.R.color.holo_red_light));
        mPaintFinal.setStyle(Paint.Style.STROKE);
        mPaintFinal.setStrokeJoin(Paint.Join.ROUND);
        mPaintFinal.setStrokeCap(Paint.Cap.ROUND);
        mPaintFinal.setStrokeWidth(TOUCH_STROKE_WIDTH);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        if (isDrawing){
            switch (mCurrentShape) {
                case LINE:
                    onDrawArrow(canvas);
                    break;
            }
        }
    }

    public void reset() {

        mPath = new Path();

    }


    @Override
    public boolean onTouchEvent (MotionEvent event) {

        //Retrieve the point
        mx = event.getX();
        my = event.getY();

        switch (mCurrentShape) {

            case ARROW:
                onTouchEventArrow(event);
                break;
            case LINE:
                onTouchEventLine(event);
                break;

        }
        return true;

    }

    //------------------------------------------------------------------
    // Arrow
    //------------------------------------------------------------------

    private void onDrawArrow(Canvas canvas) {

        float dx = Math.abs(mx - mStartX);
        float dy = Math.abs(my - mStartY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            canvas.drawLine(mStartX, mStartY, mx, my, mPaint);
        }
    }

    private void onTouchEventArrow (MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                mCanvas.drawLine(mStartX, mStartY, mx, my, mPaintFinal);
                invalidate();
                break;
        }
    }

    //------------------------------------------------------------------
    // Line
    //------------------------------------------------------------------


    private void onTouchEventLine(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;

                mPath.reset();
                mPath.moveTo(mx, my);

                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                float dx = Math.abs(mx - mStartX);
                float dy = Math.abs(my - mStartY);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    mPath.quadTo(mStartX, mStartY, (mx + mStartX) / 2, (my + mStartY) / 2);
                    mStartX = mx;
                    mStartY = my;
                }
                mCanvas.drawPath(mPath, mPaint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                mPath.lineTo(mStartX, mStartY);
                mCanvas.drawPath(mPath, mPaintFinal);
                mPath.reset();
                invalidate();
                break;
        }
    }

    //------------------------------------------------------------------
    // Undo & Redo
    //------------------------------------------------------------------

    public void onClickUndo () {
        if (paths.size()>0)
        {
            undonePaths.add(paths.remove(paths.size()-1));
            invalidate();
        }
        else
        {

        }
        //toast the user
    }

    public void onClickRedo (){
        if (undonePaths.size()>0)
        {
            paths.add(undonePaths.remove(undonePaths.size()-1));
            invalidate();
        }
        else
        {

        }
        //toast the user
    }
}
