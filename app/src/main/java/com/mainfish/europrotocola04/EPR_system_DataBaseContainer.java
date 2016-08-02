package com.mainfish.europrotocola04;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by MainFish on 08/07/16.
 */

public class EPR_system_DataBaseContainer extends SQLiteOpenHelper implements BaseColumns {

    // Имя базы данных
    private static final String DATABASE_NAME = "am_protocol.db";
    private static final String TABLE_NAME = "t1_general";

    // Версия базы данных
    private static final int DATABASE_VERSION = 1;

    // Названия столбцов
    public static final String T1_DATE = "t1_date";
    public static final String T1_TIME = "t1_time";
    public static final String T1_COUNTRY = "t1_country";
    public static final String T1_GEO = "t1_geo";
    public static final String T1_M_PLACE = "t1_m_place";
    public static final String T1_Q1="t1_q1";
    public static final String T1_Q2="t1_q2";
    public static final String T1_Q3="t1_q3";
    public static final String T1_W1="t1_w1";
    public static final String T1_W2="t1_w2";
    public static final String T1_W3="t1_w3";
    public static final String T1_W4="t1_w4";

    // Создание таблицы
    private static final String DATABASE_CREATE_SCRIPT_T1 = "create table " + TABLE_NAME + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + T1_DATE + " text, "
            + T1_TIME + " text, "
            + T1_COUNTRY + " text, "
            + T1_GEO + " text, "
            + T1_M_PLACE + " text, "
            + T1_Q1 +   " text, "
            + T1_Q2 + " text, "
            + T1_Q3 + " text, "
            + T1_W1 + " text, "
            + T1_W2 + " text, "
            + T1_W3 + " text, "
            + T1_W4 + " text);";

    EPR_system_DataBaseContainer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public EPR_system_DataBaseContainer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_SCRIPT_T1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Update table from " + oldVersion + " to " + newVersion);

        // Удаляем старую таблицу
        db.execSQL("DROP TABLE IF IT EXISTS " + TABLE_NAME);
        // Создаём новую
        onCreate(db);

    }
}
