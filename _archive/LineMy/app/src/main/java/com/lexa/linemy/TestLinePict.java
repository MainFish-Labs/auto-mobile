package com.lexa.linemy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lexa.linemy.image.PictClass;
import com.lexa.linemy.lines.ArrowArchLine;
import com.lexa.linemy.lines.ArrowArchLineTwo;
import com.lexa.linemy.lines.ArroyLine;
import com.lexa.linemy.lines.DashedLine;
import com.lexa.linemy.lines.DoubleLine;
import com.lexa.linemy.lines.EditLine;
import com.lexa.linemy.lines.EditLineOne;
import com.lexa.linemy.lines.Line;
import com.lexa.linemy.lines.StrightLine;
import com.lexa.linemy.text.TextMy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lexa on 29.05.2016.
 */
public class TestLinePict extends View {

    private Paint paintScreen;
    private PointF startPoint, endPoint;
    public static float mFieldY = 6;

    private Paint mBitmapPaint;
    private Canvas mCanvas;
    private Context context;
    private Matrix matrix;
    Bitmap mBitmap;
    Bitmap pBitmap;
    private TextMy textMy;
    private Line mLine;
    private Path mPath;

    private int isPict = 0;
    private int isPaint = 0;
    private float isText = 0;

    int rotate = 0;

    int w, h;
    int mx1, my1, mx2, my2, mx3, my3;


    private static List<Line> listLine = new ArrayList<>();
    private static List<PictClass> listCars = new ArrayList<>();
    private static List<Line> listArrow = new ArrayList<>();
    private static List<PictClass> listSigns = new ArrayList<>();
    private List<PictClass> listAdd = new ArrayList<>();
    private static List<TextMy> listText = new ArrayList<>();
    private static List<TextMy> listAddT = new ArrayList<>();
    private static List<Line> listEdit = new ArrayList<>();
    private static List<Line> listEditTwo = new ArrayList<>();

    public TestLinePict(Context context) {
        super(context);
        this.context = context;
        initScreen();
    }

    private void initScreen() {
        paintScreen = new Paint();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        paintScreen.setColor(Color.GRAY);
        paintScreen.setStyle(Paint.Style.STROKE);
        paintScreen.setPathEffect(new DashPathEffect(new float[]{2, 6, 1}, 0));
        paintScreen.setStrokeWidth(1);
        paintScreen.setAntiAlias(true);
    }


    public void setIsPaint(int isPaint) {
        this.isPaint = isPaint;

    }

    public void setNullIsPaint() {
        isPaint = 0;
    }

    public void setNullIsText() {
        isText = 0;
    }

    public void setIsText() {
        if (this.isText != 0) {
            listText.add(textMy);
        }
        isText = 0;
    }

    public void setNullIsPict() {
        isPict = 0;
        rotate = 0;
        if (matrix != null) {
            matrix.reset();
        }
    }

    public void setNullIsPict1() {
        if (isPict != 0) {
            if (isPict < 3) {
                listCars.add(new PictClass(pBitmap, matrix));
            } else {
                listSigns.add(new PictClass(pBitmap, matrix));
            }
            isPict = 0;
            rotate = 0;
            matrix = new Matrix();
            matrix.reset();

        }
    }

    public void setIsPict(int isPict) {
        boolean isAgain = false;
        if (this.isPict != 0) {
            if (isPict < 3) {
                if (this.isPict != isPict) {
                    listCars.add(new PictClass(pBitmap, matrix));
                    rotate = 0;
                } else
                    isAgain = true;
            } else {
                listSigns.add(new PictClass(pBitmap, matrix));
                rotate = 0;
            }
        }

        if (!isAgain) {
            this.isPict = isPict;

            endPoint = new PointF(200, 200);

            int idBitmap = 0;

            switch (isPict) {
                case 1:
                    idBitmap = R.drawable.car;
                    break;
                case 2:
                    idBitmap = R.drawable.carbb;
                    break;
                case 3:
                    idBitmap = R.drawable.signonebig;
                    break;
                case 4:
                    idBitmap = R.drawable.signtwobig;
                    break;
                case 5:
                    idBitmap = R.drawable.trafficlightbig;
                    break;
            }

            Bitmap bg = BitmapFactory.decodeResource(context.getResources(),
                    idBitmap);
            if (isPict <= 2)
                pBitmap = Bitmap.createScaledBitmap(bg, mx1, my1, true);
            else {
                if (isPict == 3)
                    pBitmap = Bitmap.createScaledBitmap(bg, mx2, my2, true);
                else
                    pBitmap = Bitmap.createScaledBitmap(bg, mx3, my3, true);
            }
            matrix = new Matrix();
            matrix.postTranslate(-pBitmap.getWidth() / 2, -pBitmap.getHeight() / 2);
            matrix.postRotate(rotate);
            matrix.postTranslate(200, 200);
            mCanvas.drawBitmap(pBitmap, matrix, null);
        }
    }

    public void isInvalidate() {
        invalidate();
    }


    public void isAdd(){
        if (this.isPict != 0) {
            if(listAdd ==null){
            listAdd = new ArrayList<>();}
            listAdd.add(new PictClass(pBitmap, matrix));
        }
    }

   public void isAddNul(){
       if(listAdd !=null){
           listAdd = null;
       }
   }

    public void isAddT(){
        if(this.isText != 0){
            if(listAddT ==null){
            listAddT = new ArrayList<>();}
            listAddT.add(textMy);
        }
    }

    public void isAddTNul(){
        if(listAddT !=null){
            listAddT = null;
        }
    }

    public void setIsText(float isText, String text) {
        if(this.isText != 0){
            listText.add(textMy);
        }
        this.isText = isText;

        endPoint = new PointF(200, 200);

        textMy = new TextMy(context,text, isText);
        textMy.drawMyLine(endPoint.x, endPoint.y, mCanvas);
    }


    public void rotateCar(){
        if(this.isPict != 0) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);

            matrix =  new  Matrix ();
            rotate += 45;
            matrix.postTranslate(-pBitmap.getWidth() / 2, -pBitmap.getHeight() / 2);
            matrix.postRotate(rotate);

            matrix.postTranslate(endPoint.x, endPoint.y);
            mCanvas.drawBitmap(pBitmap, matrix, null);
            invalidate();
        }
    }

    public void setListLine(List<Line> listLine) {
        this.listLine = listLine;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }

    public void setListEdit(List<Line> listEdit) {
        this.listEdit = listEdit;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }

    public void setListEditTwo(List<Line> listEditTwo) {
        this.listEditTwo = listEditTwo;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }

    public void setListArrow(List<Line> listArrow) {
        this.listArrow = listArrow;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }


    public void setListCar(List<PictClass> listCars) {
        this.listCars = listCars;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }


    public void setListSign(List<PictClass> listSign) {
        this.listSigns = listSign;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }


    public void setListText(List<TextMy> listText) {
        this.listText = listText;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w!=0) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        }
    }


    @Override
    protected void onDraw(Canvas canvas)
    {

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        w = width;
        h = height;

        mCanvas = new Canvas(mBitmap);

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);

        Path path = new Path();

        float relation =width/(float)height;

         float mx = width / (mFieldY*relation);
         float my = height / mFieldY;

        mx1 = (int) ( mx*1.9);
        my1 = (int) ( my*1.3);

        mx2 = (int) ( mx/1.5);
        my2 = (int) ( my/1.5);

        mx3 = (int) ( mx*1.2/1.5);
        my3 = (int) ( my/1.5);



        for(int i = 1; i<mFieldY*relation; i++){
            path.moveTo(mx * i, 0);
            path.lineTo(mx*i, height);}
        for(int i = 1; i<mFieldY; i++){
            path.moveTo(0, my * i);
            path.lineTo(width, my * i);
        }
        canvas.drawPath(path, paintScreen);

        if (listLine != null) {
                for (Line lists : listLine) {
                    lists.drawMyLine(lists.getX(), lists.getY(), lists.getX1(), lists.getY1(),canvas);
                }
        }

        if (listArrow != null) {
            for (Line lists : listArrow) {
                lists.drawMyLine(lists.getX(), lists.getY(), lists.getX1(), lists.getY1(),canvas);
            }
        }

        if (listEdit != null) {
            for (Line lists : listEdit) {
                lists.drawMyLineEd(lists.getPath(), canvas);
            }
        }

        if (listEditTwo != null) {
            for (Line lists : listEditTwo) {
                lists.drawMyLineEd(lists.getPath(), canvas);
            }
        }

        if (listCars != null) {
            for (PictClass car : listCars) {
                canvas.drawBitmap(car.getBitmap(), car.getMatrix(), null);
            }
        }

        if (listSigns != null) {
            for (PictClass sign  : listSigns) {
                canvas.drawBitmap(sign.getBitmap(), sign.getMatrix(), null);
            }
        }

        if (listText != null) {
            for (TextMy textMy  : listText) {
                textMy.drawMyLine(textMy.getX(), textMy.getY(), canvas);
            }
        }

        if (listAddT != null) {
            for (TextMy textMy  : listAddT) {
                textMy.drawMyLine(textMy.getX(), textMy.getY(), canvas);
            }
        }

        if (listAdd != null) {
            for (PictClass addIt : listAdd) {
                canvas.drawBitmap(addIt.getBitmap(), addIt.getMatrix(), null);
            }
        }



    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

            if(listLine.size() !=0){
                for (Line lists : listLine) {
                    lists.isLine(event.getX(), event.getY());}}

        if(isPict != 0) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    if(listAdd != null){
                        listAdd = null;
                    }
                    endPoint = new PointF(event.getX(), event.getY());
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:

                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    mCanvas = new Canvas(mBitmap);

                    matrix =  new  Matrix ();
                    matrix.postTranslate (- pBitmap.getWidth()/2 ,-pBitmap.getHeight()/2);
                    matrix.postRotate(rotate);
                    matrix.postTranslate(endPoint.x, endPoint.y);
                    mCanvas.drawBitmap ( pBitmap , matrix , null);
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:

                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    mCanvas = new Canvas(mBitmap);

                    matrix =  new  Matrix ();
                    matrix.postTranslate (- pBitmap.getWidth()/2 ,-pBitmap.getHeight()/2);
                    matrix.postRotate(rotate);
                    matrix.postTranslate(endPoint.x, endPoint.y);
                    mCanvas.drawBitmap ( pBitmap , matrix , null);
                    invalidate();
                    break;

                default:
                    break;
            }
        }


        if(isPaint != 0) {
            switch (isPaint) {
                case 1:
                    mLine = new StrightLine(context);
                    break;
                case 2:
                    mLine = new DoubleLine(context);
                    break;
                case 3:
                    mLine = new DashedLine(context);
                    break;
                case 4:
                    mLine = new ArroyLine(context);
                    break;
                case 5:
                    mLine = new ArrowArchLine(context);
                    break;
                case 6:
                    mLine = new ArrowArchLineTwo(context);
                    break;
                case 7:
                    mLine = new EditLine(context);
                    break;
                case 8:
                    break;
                case 9:
                    mLine = new EditLineOne(context);
                    break;
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPath = new Path();
                    startPoint = new PointF(event.getX(), event.getY());
                    endPoint = new PointF(event.getX(), event.getY());
                    if(isPaint ==7||isPaint ==9){
                        mPath.moveTo(event.getX(), event.getY());
                    }
                    if(isPaint == 8){
                        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                        mCanvas = new Canvas(mBitmap);
                        mLine.moveMyLine(event.getX(), event.getY(), mCanvas);
                    }
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    if(isPaint < 7) {
                        endPoint.x = event.getX();
                        endPoint.y = event.getY();
                        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                        mCanvas = new Canvas(mBitmap);
                        mLine.drawMyLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, mCanvas);
                    }
                    if(isPaint ==7||isPaint ==9){
                        float dx1 = event.getX();
                        float dy1 = event.getY();
                        float dx = Math.abs(dx1 - endPoint.x);
                        float dy = Math.abs(dy1 - endPoint.y);
                        if (dx >= 6 || dy >= 6) {
                            mPath.quadTo(endPoint.x, endPoint.y, (dx1 + endPoint.x)/2, (dy1 + endPoint.y)/2);
                            endPoint.x = dx1;
                            endPoint.y = dy1;
                        }
                        mPath.lineTo(endPoint.x, endPoint.y);
                        mLine.drawMyLineEd(mPath, mCanvas);
                    }
                    if(isPaint == 8){
                        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                        mCanvas = new Canvas(mBitmap);
                        mLine.moveMyLine(event.getX(), event.getY(), mCanvas);
                    }
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:

                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    mCanvas = new Canvas(mBitmap);
                    if(isPaint < 4){
                        mLine.drawMyLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, mCanvas);
                        listLine.add(mLine);
                        isPaint = 8;}
                    if (isPaint>=4 && isPaint<7){
                        mLine.drawMyLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, mCanvas);
                        listArrow.add(mLine);
                        isPaint = 0;}
                    if(isPaint == 7) {
                        mPath.lineTo(endPoint.x, endPoint.y);
                        mLine.drawMyLineEd(mPath, mCanvas);
                        listEdit.add(mLine);
                        isPaint = 0;
                    }
                    if(isPaint == 8){
                        mLine.moveMyLine(event.getX(), event.getY(), mCanvas);
                    }
                    if(isPaint == 9) {
                        mPath.lineTo(endPoint.x, endPoint.y);
                        mLine.drawMyLineEd(mPath, mCanvas);
                        listEditTwo.add(mLine);
                        isPaint = 0;
                    }
                    invalidate();
                    break;

                default:
                    break;
            }
        }


        if(isText != 0) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    if(listAddT != null){
                        listAddT = null;
                    }
                    endPoint = new PointF(event.getX(), event.getY());
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:

                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    mCanvas = new Canvas(mBitmap);
                     textMy.drawMyLine(endPoint.x, endPoint.y,mCanvas);
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:

                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    mCanvas = new Canvas(mBitmap);
                    textMy.drawMyLine(endPoint.x, endPoint.y,mCanvas);
                    invalidate();
                    break;

                default:
                    break;
            }
        }

        return true;
    }
}
