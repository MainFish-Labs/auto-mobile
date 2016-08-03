package by.crash.drawcrash.grid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import by.crash.drawcrash.grid.model.Arrow;
import by.crash.drawcrash.grid.model.ConstantDrawView;
import by.crash.drawcrash.grid.model.Point;
import by.crash.drawcrash.listeners.CallBackFromView;


public class ViewPlusGrid extends ImageView {

    public static final int INVALIDATE_VIEW = 1;
    public static final long TIMER_FOR_REDRAW = 10;
    static public Point pointMAX = new Point(20, 0);// */;
    static public Point pointMIN = new Point(0, -20);// */;
    static public float DrawKoeficientX = 1f;
    static public float DrawKoeficientY = 1f;
    static public float DPI_DENSITY = 1f;

    static public float W = 100;
    static public float H = 100;

    // FLAGS
    float MAX_SCALE = 10f;
    float MIN_SCALE = 0.001f;
    float DELTA_X_Y_FOR_FINGER = 10;

    // for fingers
    int stateFinger = 0;
    static final int STATE_DRAW_BITMAP = 1;
    static final int STATE_MOVE_ELEMENT = 2;
    static final int STATE_SCALE = 3;
    static final int STATE_POINT = 0;
    static final int STATE_NOTHING = 4;
    TouchPoint firstFingerDown = new TouchPoint();
    TouchPoint secondFingerDown = new TouchPoint();


    public static Point calcDrawCoordinate(Point pointDecart) {
        float x = ViewPlusGrid.DrawKoeficientX
                * (pointDecart.x - ViewPlusGrid.pointMIN.x);
        float y = ViewPlusGrid.H - ViewPlusGrid.DrawKoeficientY
                * (pointDecart.y - ViewPlusGrid.pointMIN.y);

        return new Point(x, y);
    }


    // for Timer
    Timer myTimer;
    MyTimerTask timerTask;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == INVALIDATE_VIEW) {
                invalidate();
            }
        }
    };

    // fod draw
    Arrow arrow;

    public int TYPE_ACTION = 0; // 0-nothing ; 1 - figure; 2 - arrow
    boolean drawAllowed = true;
    public boolean bitmapWasChangedFlag = false;
    CallBackFromView callBack;

    public void setTypeDrawAction(int type) {
        TYPE_ACTION = type;
    }


    public void setListener(CallBackFromView callBackFromView) {
        callBack = callBackFromView;
    }

    public void setFlagBitmapWasChanged(boolean flag) {
        bitmapWasChangedFlag = flag;
    }

    public void allowDraw() {
        drawAllowed = true;
    }

    public void banDraw() {
        drawAllowed = false;
    }


    public void setTypeAction(int type) {
        TYPE_ACTION = type;
    }

    public Bitmap saveStationImage() {
        Bitmap bitmap = getCroppedBitmap();
        setImageBitmap(bitmap);
        return bitmap;
    }

    int currentColor = Color.RED;

    public void setCurrentColor(int color) {
        arrow.setColor(color);
        currentColor = color;

    }

    public int getCurrentColor() {
        return currentColor;
    }

    public void clearCurrentDrawingElement() {
        arrow.clearRectangle();

    }

    public void setFirstPointDrawingElement(Point point) {

        Point pointStart = calkDotCoordinate(point.x, point.y);
        switch (TYPE_ACTION) {
            case ConstantDrawView.DRAW_ARROW:
                callBack.startFigureDrawn(getCroppedBitmap());
                arrow.setStartPoint(pointStart);
                break;


            default:
                break;
        }

    }

    public void setSecondPointDrawingElement(Point pointMove) {

        Point pointDot = calkDotCoordinate(pointMove.x, pointMove.y);

        switch (TYPE_ACTION) {
            case ConstantDrawView.DRAW_ARROW:
                arrow.setFinishPoint(pointDot);
                break;

            default:
                break;
        }


    }


    public ViewPlusGrid(Context context) {
        super(context);
        init(context);
    }

    public ViewPlusGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewPlusGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        myTimer.schedule(timerTask, 0, TIMER_FOR_REDRAW);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        myTimer.cancel();
    }

    Paint paintLabelRect;

    void init(Context context) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        DPI_DENSITY = dm.density;

        paintLabelRect = new Paint();
        paintLabelRect.setColor(0xffffffff);
        paintLabelRect.setStyle(Style.FILL);

        // MARGIN = Constant.SIZE_MARGIN;
        DELTA_X_Y_FOR_FINGER *= DPI_DENSITY;

        arrow = new Arrow();

        myTimer = new Timer();
        timerTask = new MyTimerTask(handler);

    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        W = w;
        // h+=MARGIN;
        H = h;

        calcDrawKoeficient();
    }

    int kostil = 0;

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        arrow.draw(canvas);

    }

    public Bitmap getBitmap() {

        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache(true);
        if (this.getDrawingCache(true) != null) {
            Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache(true));
            this.setDrawingCacheEnabled(false);

            return bitmap;
        }
        return null;
    }

    public Bitmap getCroppedBitmap() {

        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache(true);
        if (this.getDrawingCache(true) != null) {
            Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache(true));
            this.setDrawingCacheEnabled(false);

            // return cutBitmap(bitmap);
            return bitmap;
        }
        return null;
    }

    public static Bitmap cutBitmap(Bitmap bitmap) {

        int emptyTop = 0;
        int emptyLeft = 0;
        int emptyRight = 0;
        int emptyBottom = 0;

        // ищем пустые строки сверху
        outloop:
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                if (bitmap.getPixel(j, i) != 0) {
                    break outloop;
                }
            }
            emptyTop++;
        }
        // ищем пустые столбцы слева
        outloop2:
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                if (bitmap.getPixel(i, j) != 0) {
                    break outloop2;
                }
            }
            emptyLeft++;
        }
        // ищем пустые строки снизу
        outloop3:
        for (int i = (bitmap.getHeight() - 1); i > 0; i--) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                if (bitmap.getPixel(j, i) != 0) {
                    break outloop3;
                }
            }
            emptyBottom++;
        }
        // ищем пустые столбцы справа
        outloop4:
        for (int i = (bitmap.getWidth() - 1); i > 0; i--) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                if (bitmap.getPixel(i, j) != 0) {
                    break outloop4;
                }
            }
            emptyRight++;
        }

        final Bitmap croppedBitmap = Bitmap.createBitmap(bitmap,
                (int) emptyLeft, (int) emptyTop, (int) bitmap.getWidth()
                        - emptyLeft - emptyRight, (int) bitmap.getHeight()
                        - emptyBottom - emptyTop);

        return croppedBitmap;

    }

    boolean inTouch;

    private void onTouchTwo(MotionEvent event) {
        int actionMask = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: // Первый палец вниз

                inTouch = true;
                // idFirstFinger = event.getPointerId(pointerIndex);
                firstFingerDown.point.x = event.getX(pointerIndex);
                firstFingerDown.point.y = event.getY(pointerIndex);
                firstFingerDown.id = event.getPointerId(pointerIndex);
                if (drawAllowed) {
                    setFirstPointDrawingElement(firstFingerDown.point);
                }

                stateFinger = STATE_POINT;
                break;

            case MotionEvent.ACTION_UP: // последний палец поднялся



                    if (drawAllowed)
                        callBack.figureDrawn(getCroppedBitmap());


                stateFinger = STATE_NOTHING;

                setFlagBitmapWasChanged(true);
                clearCurrentDrawingElement();


            case MotionEvent.ACTION_MOVE:
                if (drawAllowed) {
                    if (pointerCount < 2) {
                        if (stateFinger == STATE_POINT
                                || stateFinger == STATE_DRAW_BITMAP) {
                            stateFinger = STATE_DRAW_BITMAP;
                            Point pointMove = new Point(event.getX(), event.getY());

                            setSecondPointDrawingElement(pointMove);

                        }
                    }
                }

                break;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        onTouchTwo(event);

        return true;
    }


    public static Point calkDotCoordinate(float x, float y) {
        y = H - y;

        float x1 = (x * (pointMAX.x - pointMIN.x) / W) + pointMIN.x;
        float y1 = (y * (pointMAX.y - pointMIN.y) / H) + pointMIN.y;

        return new Point(x1, y1);

    }

    public void calcDrawKoeficient() {
        DrawKoeficientX = (ViewPlusGrid.W / (ViewPlusGrid.pointMAX.x - ViewPlusGrid.pointMIN.x));
        DrawKoeficientY = (ViewPlusGrid.H / (ViewPlusGrid.pointMAX.y - ViewPlusGrid.pointMIN.y));
    }

    public void changeMaxMin(Point pMAX, Point pMIN) {
        pointMAX.x = pMAX.x;
        pointMAX.y = pMAX.y;
        pointMIN.x = pMIN.x;
        pointMIN.y = pMIN.y;
        calcDrawKoeficient();
        invalidate();

    }

    private class TouchPoint {
        int id;
        Point point = new Point();
    }


    class MyTimerTask extends TimerTask {

        Handler handlerTIMER;

        public MyTimerTask(Handler handler) {
            handlerTIMER = handler;
        }

        public void run() {
            handlerTIMER.sendEmptyMessage(INVALIDATE_VIEW);
        }
    }

}
