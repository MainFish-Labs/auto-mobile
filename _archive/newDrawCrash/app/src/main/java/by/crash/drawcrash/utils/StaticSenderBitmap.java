package by.crash.drawcrash.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class StaticSenderBitmap {
	public static Bitmap bitmap;
	public static int degrees = 0;

	public static void setBitmap(Bitmap b) {
		bitmap = b;
		degrees = 0;
	}

	// public static int getAngle() {
	// return angle_rotate;
	// }
	//
	// public static void incrementAngle() {
	// angle_rotate += 90;
	// }

	public static Bitmap rotateImage() {
		degrees += 90;
		Matrix matrix = new Matrix();
		matrix.postRotate(degrees);
		if (bitmap == null)
			return null;
		Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);

		// try {
		// setImageBitmap(ViewPlusGrid.cutBitmap(bitmap));
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		return bitmap1;
	}
	
	public static Bitmap getBitmapСonsideringAngle() {
		
		Matrix matrix = new Matrix();
		matrix.postRotate(degrees);
		if (bitmap == null)
			return null;
		Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);

		// try {
		// setImageBitmap(ViewPlusGrid.cutBitmap(bitmap));
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		return bitmap1;
	}

}
