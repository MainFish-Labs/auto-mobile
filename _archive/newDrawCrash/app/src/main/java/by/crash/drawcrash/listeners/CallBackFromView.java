package by.crash.drawcrash.listeners;

import android.graphics.Bitmap;

import by.crash.drawcrash.grid.model.Point;


public interface CallBackFromView {

	public void startFigureDrawn(Bitmap bitmap);

	public void figureDrawn(Bitmap bitmap);

	public void openKeyBoard(Point startpoint);
	

}
