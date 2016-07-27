package com.mainfish.europrotocola04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by artli on 05.03.2016.
 */
public class Circumstances extends AppCompatActivity {

    public int countA01, countB01, countA02, countB02, countA03, countB03, countA04, countB04, countA05, countB05, countA06, countB06, countA07, countB07, countA08, countB08, countA09, countB09, countA10, countB10, countA11, countB11, countA12, countB12, countA13, countB13, countA14, countB14, countA15, countB15, countA16, countB16, countA17, countB17, countA18, countB18;

    public TextView CountA;
    public TextView CountB;

    private String[] infoText;
    private ListView mDrawerListView;

    private static final int LONG_DELAY = 3500; // 3.5 seconds
    private static final int SHORT_DELAY = 2000; // 2 seconds

    public boolean mSlideState;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.circumstances);

        infoText = getResources().getStringArray(R.array.info_button4);
        mDrawerListView = (ListView) findViewById(R.id.info_drawer);

        // подключим адаптер для списка
        mDrawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.info_drawer_layout, infoText));

         SwitchCompat switch_a_1 = (SwitchCompat) findViewById(R.id.circum_a_01);
         SwitchCompat switch_b_1 = (SwitchCompat) findViewById(R.id.circum_b_01);
         SwitchCompat switch_a_2 = (SwitchCompat) findViewById(R.id.circum_a_02);
         SwitchCompat switch_b_2 = (SwitchCompat) findViewById(R.id.circum_b_02);
         SwitchCompat switch_a_3 = (SwitchCompat) findViewById(R.id.circum_a_03);
         SwitchCompat switch_b_3 = (SwitchCompat) findViewById(R.id.circum_b_03);
         SwitchCompat switch_a_4 = (SwitchCompat) findViewById(R.id.circum_a_04);
         SwitchCompat switch_b_4 = (SwitchCompat) findViewById(R.id.circum_b_04);
         SwitchCompat switch_a_5 = (SwitchCompat) findViewById(R.id.circum_a_05);
         SwitchCompat switch_b_5 = (SwitchCompat) findViewById(R.id.circum_b_05);
         SwitchCompat switch_a_6 = (SwitchCompat) findViewById(R.id.circum_a_06);
         SwitchCompat switch_b_6 = (SwitchCompat) findViewById(R.id.circum_b_06);
         SwitchCompat switch_a_7 = (SwitchCompat) findViewById(R.id.circum_a_07);
         SwitchCompat switch_b_7 = (SwitchCompat) findViewById(R.id.circum_b_07);
         SwitchCompat switch_a_8 = (SwitchCompat) findViewById(R.id.circum_a_08);
         SwitchCompat switch_b_8 = (SwitchCompat) findViewById(R.id.circum_b_08);
         SwitchCompat switch_a_9 = (SwitchCompat) findViewById(R.id.circum_a_09);
         SwitchCompat switch_b_9 = (SwitchCompat) findViewById(R.id.circum_b_09);
         SwitchCompat switch_a_10 = (SwitchCompat) findViewById(R.id.circum_a_10);
         SwitchCompat switch_b_10 = (SwitchCompat) findViewById(R.id.circum_b_10);
         SwitchCompat switch_a_11 = (SwitchCompat) findViewById(R.id.circum_a_11);
         SwitchCompat switch_b_11 = (SwitchCompat) findViewById(R.id.circum_b_11);
         SwitchCompat switch_a_12 = (SwitchCompat) findViewById(R.id.circum_a_12);
         SwitchCompat switch_b_12 = (SwitchCompat) findViewById(R.id.circum_b_12);
         SwitchCompat switch_a_13 = (SwitchCompat) findViewById(R.id.circum_a_13);
         SwitchCompat switch_b_13 = (SwitchCompat) findViewById(R.id.circum_b_13);
         SwitchCompat switch_a_14 = (SwitchCompat) findViewById(R.id.circum_a_14);
         SwitchCompat switch_b_14 = (SwitchCompat) findViewById(R.id.circum_b_14);
         SwitchCompat switch_a_15 = (SwitchCompat) findViewById(R.id.circum_a_15);
         SwitchCompat switch_b_15 = (SwitchCompat) findViewById(R.id.circum_b_15);
         SwitchCompat switch_a_16 = (SwitchCompat) findViewById(R.id.circum_a_16);
         SwitchCompat switch_b_16 = (SwitchCompat) findViewById(R.id.circum_b_16);
         SwitchCompat switch_a_17 = (SwitchCompat) findViewById(R.id.circum_a_17);
         SwitchCompat switch_b_17 = (SwitchCompat) findViewById(R.id.circum_b_17);
         SwitchCompat switch_a_18 = (SwitchCompat) findViewById(R.id.circum_a_18);
         SwitchCompat switch_b_18 = (SwitchCompat) findViewById(R.id.circum_b_18);

        /**
         * Обработка свитчей
         */


        switch_a_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Не соблюдал безопасную дистанцию" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA01=1;
                } else {
                    countA01=0;
                }

                countResultA();
                
            }
        });



        switch_b_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Не соблюдал безопасную дистанцию" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB01=1;
                } else {
                    countB01=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Не соблюдал необходимый боковой интервал" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA02=1;
                } else {
                    countA02=0;
                }

                countResultA();

            }
        });



        switch_b_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Не соблюдал необходимый боковой интервал" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB02=1;
                } else {
                    countB02=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Перестраивался в другую полосу" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA03=1;
                } else {
                    countA03=0;
                }

                countResultA();

            }
        });



        switch_b_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Перестраивался в другую полосу" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB03=1;
                } else {
                    countB03=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Поворачивал направо" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA04=1;
                } else {
                    countA04=0;
                }

                countResultA();

            }
        });



        switch_b_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Поворачивал направо" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB04=1;
                } else {
                    countB04=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Поворачивал налево" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA05=1;
                } else {
                    countA05=0;
                }

                countResultA();

            }
        });



        switch_b_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Поворачивал налево" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB05=1;
                } else {
                    countB05=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Разворачивался" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA06=1;
                } else {
                    countA06=0;
                }

                countResultA();

            }
        });



        switch_b_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Разворачивался" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB06=1;
                } else {
                    countB06=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Двигался задним ходом" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA07=1;
                } else {
                    countA07=0;
                }

                countResultA();

            }
        });



        switch_b_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Двигался задним ходом" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB07=1;
                } else {
                    countB07=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Съезжал с проезжей части дороги" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA08=1;
                } else {
                    countA08=0;
                }

                countResultA();

            }
        });



        switch_b_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Съезжал с проезжей части дороги" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB08=1;
                } else {
                    countB08=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Выехал на перекресток на запрещающий сигнал светофора" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA09=1;
                } else {
                    countA09=0;
                }

                countResultA();

            }
        });



        switch_b_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Выехал на перекресток на запрещающий сигнал светофора" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB09=1;
                } else {
                    countB09=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Выехал на полосу встречного движения" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA10=1;
                } else {
                    countA10=0;
                }

                countResultA();

            }
        });



        switch_b_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Выехал на полосу встречного движения" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB10=1;
                } else {
                    countB10=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Нарушил правила обгона" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA11=1;
                } else {
                    countA11=0;
                }

                countResultA();

            }
        });



        switch_b_11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Нарушил правила обгона" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB11=1;
                } else {
                    countB11=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Начинал движение после остановки, стоянки" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA12=1;
                } else {
                    countA12=0;
                }

                countResultA();

            }
        });



        switch_b_12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Начинал движение после остановки, стоянки" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB12=1;
                } else {
                    countB12=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Не выполнил требования знака приоритета" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA13=1;
                } else {
                    countA13=0;
                }

                countResultA();

            }
        });



        switch_b_13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Не выполнил требования знака приоритета" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB13=1;
                } else {
                    countB13=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Выезжал со второстепенной дороги, прилегающей территории" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA14=1;
                } else {
                    countA14=0;
                }

                countResultA();

            }
        });



        switch_b_14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Выезжал со второстепенной дороги, прилегающей территории" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB14=1;
                } else {
                    countB14=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Двигался по прилегающей территории при наличии препятствия справа" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA15=1;
                } else {
                    countA15=0;
                }

                countResultA();

            }
        });



        switch_b_15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Двигался по прилегающей территории при наличии препятствия справа" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB15=1;
                } else {
                    countB15=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Двигался по перекрестку с круговым движением" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA16=1;
                } else {
                    countA16=0;
                }

                countResultA();

            }
        });



        switch_b_16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Двигался по перекрестку с круговым движением" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB16=1;
                } else {
                    countB16=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Совершил наезд на стоящее ТС" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA17=1;
                } else {
                    countA17=0;
                }

                countResultA();

            }
        });



        switch_b_17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Совершил наезд на стоящее ТС" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB17=1;
                } else {
                    countB17=0;
                }

                countResultB();

            }
        });

        /**  */

        /**
         * Обработка свитчей
         */


        switch_a_18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Иное нарушение, неуказанное в подпунктах 1–17" + ".\nВодитель ТС А - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countA18=1;
                } else {
                    countA18=0;
                }

                countResultA();

            }
        });



        switch_b_18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(Circumstances.this, "Иное нарушение, неуказанное в подпунктах 1–17" + ".\nВодитель ТС B - " + (isChecked ? "Да" : "Нет"),Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    countB18=1;
                } else {
                    countB18=0;
                }

                countResultB();

            }
        });

        /**  */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_circum);
        mSlideState = false;

        mDrawerLayout.setDrawerListener(new ActionBarDrawerToggle(this,
                mDrawerLayout,
                0,
                0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mSlideState=false;//is Closed
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSlideState=true;//is Opened
            }});

    }

    public void countResultA() {

        int Result = (countA01 + countA02 + countA03 + countA04 + countA05 + countA06 + countA07 + countA08 + countA09 + countA10 + countA11 + countA12 + countA13 + countA14 + countA15 + countA16 + countA17 + countA18);

        String CountResult = "" + Result;

        CountA = (TextView) findViewById(R.id.total_a);
        CountA.setText(CountResult);

    }

    public void countResultB() {

        int Result = (countB01 + countB02 + countB03 + countB04 + countB05 + countB06 + countB07 + countB08 + countB09 + countB10 + countB11 + countB12 + countB13 + countB14 + countB15 + countB16 + countB17 + countB18);

        String CountResult = "" + Result;

        CountB = (TextView) findViewById(R.id.total_b);
        CountB.setText(CountResult);

    }



    public void gotoSchemePage (View view) {

        Intent intentSchemePage = new Intent(this, SchemePage.class);
        startActivity(intentSchemePage);

    }

    public void gotoDriverB (View view) {

        Intent intentDriverB = new Intent(this, DriverB.class);
        startActivity(intentDriverB);

    }

    public void btnOpenI (View view) {

        //mDrawerListView = (ListView) findViewById(R.id.info_drawer);
        //mDrawerListView.setVisibility(View.VISIBLE);

        if(mSlideState){
            mDrawerLayout.closeDrawer(GravityCompat.END);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.END);
        }

    }



}