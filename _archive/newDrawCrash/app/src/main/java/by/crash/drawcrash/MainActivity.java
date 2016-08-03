package by.crash.drawcrash;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import by.crash.drawcrash.grid.model.ConstantDrawView;
import by.crash.drawcrash.grid.model.Point;
import by.crash.drawcrash.grid.view.ViewPlusGrid;
import by.crash.drawcrash.listeners.CallBackFromView;

public class MainActivity extends AppCompatActivity implements CallBackFromView {

    @Bind(R.id.image_for_draw)
    ViewPlusGrid viewPlusGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewPlusGrid.setTypeAction(ConstantDrawView.DRAW_ARROW);
        viewPlusGrid.setListener(this);

    }

    @Override
    public void startFigureDrawn(Bitmap bitmap) {

    }

    @Override
    public void figureDrawn(Bitmap bitmap) {

        viewPlusGrid.setImageBitmap(bitmap);
    }


    @Override
    public void openKeyBoard(Point startpoint) {

    }
}
