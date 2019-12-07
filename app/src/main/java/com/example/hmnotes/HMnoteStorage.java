package com.example.hmnotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HMnoteStorage extends SQLiteOpenHelper {

    private static final int versionNumber = 1;
    private static final String storageName = "HMnote";

    static final String TABLE_NAME = "storage";
    static final String COL_ID = "id";
    static final String COL_TITLE = "title";
    static final String COL_DETAIL = "detail";
    static final String COL_CREATE_DATE = "create_date";
    static final String COL_CREATE_TIME = "create_time";

    HMnoteStorage(Context context) {
        super(context, storageName, null, versionNumber);
    }
    @Override
    public void onCreate(SQLiteDatabase noteStorage) {

        String sqlCommand = "CREATE TABLE " + TABLE_NAME +
                "(" + COL_ID + " INT PRIMARY KEY, " +
                COL_TITLE + " TEXT, " + COL_DETAIL + " TEXT, " +
                COL_CREATE_DATE + " TEXT, " + COL_CREATE_TIME + " TEXT)";
        noteStorage.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase noteStorage, int previousVersion, int updatedVersion) {
        if (updatedVersion <= previousVersion) {
            return;
        }
        noteStorage.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(noteStorage);

    }
}
