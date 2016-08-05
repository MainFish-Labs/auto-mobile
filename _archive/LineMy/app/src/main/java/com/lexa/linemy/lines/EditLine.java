package com.lexa.linemy.lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * Created by Lexa on 12.06.2016.
 */
public class EditLine extends Line {

    private Paint paintLine;
    private Path path;

    public EditLine(Context context) {
        super(context);
        paintLine = super.paintLine;
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
    public void drawMyLineEd(Path path,Canvas canvas) {
        this.path = new Path();
        this.path = path;
        canvas.drawPath(path, paintLine);
    }

}
