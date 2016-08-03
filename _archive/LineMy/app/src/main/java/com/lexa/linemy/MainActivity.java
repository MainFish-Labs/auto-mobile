package com.lexa.linemy;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lexa.linemy.fragment_button.FragmentStepFive;
import com.lexa.linemy.fragment_button.FragmentStepFour;
import com.lexa.linemy.fragment_button.FragmentStepIsInfo;
import com.lexa.linemy.fragment_button.FragmentStepOne;
import com.lexa.linemy.fragment_button.FragmentStepSix;
import com.lexa.linemy.fragment_button.FragmentStepThree;
import com.lexa.linemy.fragment_button.FragmentStepTwo;
import com.lexa.linemy.fragment_info.FragmentInforPanelOne;
import com.lexa.linemy.fragment_info.FragmentInforPanelTwo;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements    FragmentInforPanelOne.OnFragmentInfoListener,
                                                                  FragmentInforPanelTwo.OnFragmentInfoListenerAll,
                                                                  FragmentStepOne.OnFragmentStateOne,
                                                                  FragmentStepTwo.OnFragmentStateTwo,
                                                                  FragmentStepThree.OnFragmentStateThree,
                                                                  FragmentStepFour.OnFragmentStateFour,
                                                                  FragmentStepFive.OnFragmentStateFive,
                                                                  FragmentStepSix.OnFragmentStateSix{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    FragmentInforPanelOne fragmentInforPanelOne;
    FragmentStepOne fragmentStepOne;
    FragmentStepTwo fragmentStepTwo;
    FragmentStepThree fragmentStepThree;
    FragmentStepFour fragmentStepFour;
    FragmentStepFive fragmentStepFive;
    FragmentStepSix fragmentStepSix;
    TestLinePict myView;

    int step = 0;

    FrameLayout v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        v = (FrameLayout) findViewById(R.id.my_view);
        if(myView == null) {
            myView = new TestLinePict(this);
            v.addView(myView);
        }

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState != null){
            step = savedInstanceState.getInt("please");
        }

        if(savedInstanceState == null) {
            fragmentStepOne = FragmentStepOne.newInstance();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, fragmentStepOne);
            fragmentTransaction.commit();

            fragmentInforPanelOne = FragmentInforPanelOne.newInstance(step);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.conteiner2, fragmentInforPanelOne);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        onFragmentInfoAll();
        super.onPause();
    }

    public void chooseButton(){
        switch (step) {
            case 0:
                fragmentStepOne= FragmentStepOne.newInstance();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentStepOne);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentStepTwo= FragmentStepTwo.newInstance();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentStepTwo);
                fragmentTransaction.commit();
                myView.isInvalidate();
                break;
            case 2:
                fragmentStepThree= FragmentStepThree.newInstance();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentStepThree);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentStepFour= FragmentStepFour.newInstance();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentStepFour);
                fragmentTransaction.commit();
                myView.isInvalidate();
                break;
            case 4:
                fragmentStepFive= FragmentStepFive.newInstance();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentStepFive);
                fragmentTransaction.commit();
                break;
            case 5:
                fragmentStepSix= FragmentStepSix.newInstance();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentStepSix);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onFragmentInfo() {
        switch (step) {
            case 1:
                myView.isAdd();
                break;
            case 3:
                myView.isAdd();
                break;
            case 4:
                myView.isAddT();
                break;
        }

        FragmentInforPanelTwo fragmentInforPanelTwo = FragmentInforPanelTwo.newInstance(step);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conteiner2, fragmentInforPanelTwo);
        fragmentTransaction.commit();

        FragmentStepIsInfo fragmentStepIsInfo = FragmentStepIsInfo.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepIsInfo);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInfoAll() {
        fragmentInforPanelOne = FragmentInforPanelOne.newInstance(step);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conteiner2, fragmentInforPanelOne);
        fragmentTransaction.commit();
        chooseButton();
    }

    @Override
    public void onFragmentStateOne(int i) {
        myView.setIsPaint(i);
    }

    @Override
    public void onFragmentStateOneCancel() {
        List<Line> listLine = new ArrayList<>();
        myView.setListLine(listLine);
        List<Line> listEditTwo = new ArrayList<>();
        myView.setListEditTwo(listEditTwo);
        myView.setNullIsPaint();
    }

    @Override
    public void onFragmentStateOneSave() {
        myView.setNullIsPaint();
        fragmentStepTwo= FragmentStepTwo.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepTwo);
        fragmentTransaction.commit();
        step =1;
        onFragmentInfoAll();
    }

    @Override
    public void onFragmentStateTwo(int i) {
        myView.setIsPict(i);
    }

    @Override
    public void onFragmentStateTwoCancel() {
        List<PictClass> listCars = new ArrayList<>();
        myView.setListCar(listCars);
        myView.isAddNul();
        myView.setNullIsPict();
    }

    @Override
    public void onFragmentStateTwoRotate() {
        myView.rotateCar();
    }

    @Override
    public void onFragmentStateTwoCancelAll() {
        List<Line> listLine = new ArrayList<>();
        myView.setListLine(listLine);
        List<Line> listEditTwo = new ArrayList<>();
        myView.setListEditTwo(listEditTwo);
        myView.setNullIsPaint();

        List<PictClass> listCars = new ArrayList<>();
        myView.setListCar(listCars);
        myView.setNullIsPict();

        step =0;
        onFragmentInfoAll();
        myView.isAddNul();

        fragmentStepOne= FragmentStepOne.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepOne);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentStateTwoSave() {
        myView.setNullIsPict1();

        step =2;
        onFragmentInfoAll();
        myView.isAddNul();

        fragmentStepThree= FragmentStepThree.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepThree);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentStateThree(int i) {
              myView.setIsPaint(i);
    }

    @Override
    public void onFragmentStateThreeCancel() {
        List<Line> listArrow = new ArrayList<>();
        myView.setListArrow(listArrow);
    }

    @Override
    public void onFragmentStateThreeCancelAll() {

        List<Line> listLine = new ArrayList<>();
        myView.setListLine(listLine);
        List<Line> listEditTwo = new ArrayList<>();
        myView.setListEditTwo(listEditTwo);
        myView.setNullIsPaint();

        List<PictClass> listCars = new ArrayList<>();
        myView.setListCar(listCars);
        myView.setNullIsPict();

        List<Line> listArrow = new ArrayList<>();
        myView.setListArrow(listArrow);
        myView.setNullIsPaint();

        List<PictClass> listSign = new ArrayList<>();
        myView.setListSign(listSign);
        myView.setNullIsPict();

        fragmentStepOne= FragmentStepOne.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepOne);
        fragmentTransaction.commit();
        step =0;
        onFragmentInfoAll();
    }

    @Override
    public void onFragmentStateThreeSave() {
        myView.setNullIsPaint();
        fragmentStepFour= FragmentStepFour.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepFour);
        fragmentTransaction.commit();
        step =3;
        onFragmentInfoAll();
    }

    @Override
    public void onFragmentStateFour(int i) {
        myView.setIsPict(i);
    }

    @Override
    public void onFragmentStateFourCancel() {
        List<PictClass> listSign = new ArrayList<>();
        myView.setListSign(listSign);
        myView.setNullIsPict();
        myView.isAddNul();
    }

    @Override
    public void onFragmentStateFourCancelAll() {
        List<Line> listLine = new ArrayList<>();
        myView.setListLine(listLine);
        List<Line> listEditTwo = new ArrayList<>();
        myView.setListEditTwo(listEditTwo);
        myView.setNullIsPaint();

        List<PictClass> listCars = new ArrayList<>();
        myView.setListCar(listCars);
        myView.setNullIsPict();

        List<Line> listArrow = new ArrayList<>();
        myView.setListArrow(listArrow);
        myView.setNullIsPaint();

        List<PictClass> listSign = new ArrayList<>();
        myView.setListSign(listSign);
        myView.setNullIsPict();

        step =0;
        onFragmentInfoAll();
        myView.isAddNul();

        fragmentStepOne= FragmentStepOne.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepOne);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentStateFourSave() {
        step =4;
        onFragmentInfoAll();
        myView.isAddNul();
        myView.setNullIsPict1();
        fragmentStepFive= FragmentStepFive.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepFive);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentStateFive(float i,String text) {
        myView.setIsText(i, text);
    }

    @Override
    public void onFragmentStateFiveCancel() {
        List<TextMy> listText = new ArrayList<>();
        myView.setListText(listText);
        myView.setNullIsText();
        myView.isAddTNul();
    }

    @Override
    public void onFragmentStateFiveCancelAll() {
        List<Line> listLine = new ArrayList<>();
        myView.setListLine(listLine);
        List<Line> listEditTwo = new ArrayList<>();
        myView.setListEditTwo(listEditTwo);
        myView.setNullIsPaint();

        List<PictClass> listCars = new ArrayList<>();
        myView.setListCar(listCars);
        myView.setNullIsPict();

        List<Line> listArrow = new ArrayList<>();
        myView.setListArrow(listArrow);
        myView.setNullIsPaint();

        List<PictClass> listSign = new ArrayList<>();
        myView.setListSign(listSign);
        myView.setNullIsPict();

        List<TextMy> listText = new ArrayList<>();
        myView.setListText(listText);
        myView.setNullIsText();

        step =0;
        onFragmentInfoAll();
        myView.isAddTNul();

        fragmentStepOne= FragmentStepOne.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepOne);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentStateFiveSave() {
        myView.setIsText();
        step =5;
        onFragmentInfoAll();
        myView.isAddTNul();
        fragmentStepSix= FragmentStepSix.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepSix);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentStateSix(int i) {
        myView.setIsPaint(i);
    }

    @Override
    public void onFragmentStateSixCancel() {
        List<Line> listEdit = new ArrayList<>();
        myView.setListEdit(listEdit);
    }

    @Override
    public void onFragmentStateSixCancelAll() {
        List<Line> listLine = new ArrayList<>();
        myView.setListLine(listLine);
        List<Line> listEditTwo = new ArrayList<>();
        myView.setListEditTwo(listEditTwo);
        myView.setNullIsPaint();

        List<PictClass> listCars = new ArrayList<>();
        myView.setListCar(listCars);
        myView.setNullIsPict();

        List<Line> listArrow = new ArrayList<>();
        myView.setListArrow(listArrow);
        myView.setNullIsPaint();

        List<PictClass> listSign = new ArrayList<>();
        myView.setListSign(listSign);
        myView.setNullIsPict();

        List<TextMy> listText = new ArrayList<>();
        myView.setListText(listText);
        myView.setNullIsText();

        List<Line> listEdit = new ArrayList<>();
        myView.setListEdit(listEdit);
        myView.setNullIsPaint();
        step =0;

        onFragmentInfoAll();

        fragmentStepOne= FragmentStepOne.newInstance();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentStepOne);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentStateSixSave() {
        v.setDrawingCacheEnabled(true);
        Bitmap b = v.getDrawingCache();

        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
        String output_file_name = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+ File.separator +  timeStamp+".png";

        File pictureFile = new File(output_file_name);
        if (pictureFile.exists()) {
            pictureFile.delete();
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pictureFile);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Toast.makeText(this,"Схема успешно сохранена", Toast.LENGTH_SHORT).show();
            fos.flush();
            fos.close();
            v.setDrawingCacheEnabled(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException es) {
            es.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
            List<Line> listLine = new ArrayList<>();
            myView.setListLine(listLine);
            List<Line> listEditTwo = new ArrayList<>();
            myView.setListEditTwo(listEditTwo);
            myView.setNullIsPaint();

            List<PictClass> listCars = new ArrayList<>();
            myView.setListCar(listCars);
            myView.setNullIsPict();

            List<Line> listArrow = new ArrayList<>();
            myView.setListArrow(listArrow);
            myView.setNullIsPaint();

            List<PictClass> listSign = new ArrayList<>();
            myView.setListSign(listSign);
            myView.setNullIsPict();

            List<TextMy> listText = new ArrayList<>();
            myView.setListText(listText);
            myView.setNullIsText();

            List<Line> listEdit = new ArrayList<>();
            myView.setListEdit(listEdit);
            myView.setNullIsPaint();
            step = 0;
            finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("please", step);
    }
}
