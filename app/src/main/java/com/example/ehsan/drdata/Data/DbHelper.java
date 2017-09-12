package com.example.ehsan.drdata.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ehsan-HP on 20/07/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "data.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE " + DataContract.DataEnty.TABLE_NAME + " (" +
                DataContract.DataEnty._ID + " INTEGER PRIMARY KEY," +
                DataContract.DataEnty.COLUMN_GIVEN_NAME + " TEXT NOT NULL, " +
                DataContract.DataEnty.COLUMN_SURE_NAME + " TEXT NOT NULL, " +
                DataContract.DataEnty.COLUMN_PHONE + " TEXT NOT NULL, " +
                DataContract.DataEnty.COLUMN_SEX + " TEXT NOT NULL, " +
                DataContract.DataEnty.COLUMN_DISEASE + " TEXT NOT NULL" +
                " );";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DataContract.DataEnty.TABLE_NAME);
        onCreate(db);
    }
}
