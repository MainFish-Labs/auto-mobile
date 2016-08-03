package com.lexa.linemy.image;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Lexa on 07.06.2016.
 */
public class PictClass {

    private Bitmap bitmap;
    private Matrix matrix;


    public PictClass(Bitmap bitmap, Matrix matrix) {
        this.bitmap = bitmap;
        this.matrix = matrix;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
