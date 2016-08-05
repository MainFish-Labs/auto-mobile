package by.crash.drawcrash.grid.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import by.crash.drawcrash.grid.view.ViewPlusGrid;


public class Arrow extends Element {

    Point pointStart;
    Point pointFinish;

    Paint paintLine;
    float lineWidth = 10;


    public Arrow() {
        super(ConstantDrawView.TYPE_POLYGON);
        init();
    }


    void init() {

        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setColor(0xffff3351);
        paintLine.setStrokeWidth(lineWidth);
        paintLine.setAntiAlias(true);

    }

    public void setPoints(Point first, Point second) {
        pointStart = first;
        pointFinish = second;
    }

    public void setStartPoint(Point first) {
        pointStart = first;
    }

    public void setFinishPoint(Point second) {
        pointFinish = second;
    }

    public void clearRectangle() {
        pointStart = null;
        pointFinish = null;
    }

    @Override
    public void draw(Canvas canvas) {

        if (pointStart != null && pointFinish != null) {

            Point point = ViewPlusGrid.calcDrawCoordinate(pointStart);
            Point point2 = ViewPlusGrid.calcDrawCoordinate(pointFinish);

            double angle = Math.atan2(point2.y - point.y, point2.x - point.x);

            drawSingleArrow(canvas, point, point2, angle);

        }

    }

    private void drawSingleArrow(Canvas canvas, Point pointStart, Point pointEnd, double angle) {

        float lenght2 = 60;

        Point rightPoint = new Point((float) (pointEnd.x - lenght2
                * Math.cos(angle - Math.PI / 5)), (float) (pointEnd.y - lenght2
                * Math.sin(angle - Math.PI / 5)));

        Point leftPoint = new Point((float) (pointEnd.x - lenght2
                * Math.cos(angle + Math.PI / 5)), (float) (pointEnd.y - lenght2
                * Math.sin(angle + Math.PI / 5)));


        canvas.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y, paintLine);
        canvas.drawLine(pointEnd.x, pointEnd.y, rightPoint.x, rightPoint.y, paintLine);
        canvas.drawLine(pointEnd.x, pointEnd.y, leftPoint.x, leftPoint.y, paintLine);


        canvas.drawCircle(pointStart.x, pointStart.y, lineWidth / 2, paintLine);
        canvas.drawCircle(pointEnd.x, pointEnd.y, lineWidth / 2, paintLine);
        canvas.drawCircle(leftPoint.x, leftPoint.y, lineWidth / 2, paintLine);
        canvas.drawCircle(rightPoint.x, rightPoint.y, lineWidth / 2, paintLine);

    }


    @Override
    public int getColor() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setColor(int color) {
        paintLine.setColor(color);

    }

    @Override
    public float getWidthLine() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWidthLine(float widthLine) {
        // TODO Auto-generated method stub

    }

}
