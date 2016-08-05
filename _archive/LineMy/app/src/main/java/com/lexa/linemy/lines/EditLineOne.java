package com.lexa.linemy.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Lexa on 26.06.2016.
 */
public class EditLineOne extends Line {

    private Paint paintLine;
    private Path path;

    public EditLineOne(Context context) {
        super(context);
        paintLine = super.paintLine;
        paintLine.setStrokeWidth(8);
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public void drawMyLine(float x, float y, float x1, float y1, Canvas canvas) {
    }

    @Override
    public boolean isLine(float x2, float y2) {
        return false;
    }

    @Override
    public void drawMyLineEd(Path path, Canvas canvas) {
        this.path = new Path();
        this.path = path;
        canvas.drawPath(path, paintLine);
    }
}
