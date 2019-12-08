package com.example.hmnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class HMnoteStorage extends SQLiteOpenHelper {

    private static final int versionNumber = 4;
    private static final String storageName = "HMnote";

    static final String DATABASE_NAME = "storage";
    static final String COL_ID = "id";
    static final String COL_SUBJECT = "subject";
    static final String COL_DETAIL = "detail";
    static final String COL_CREATE_DATE = "create_date";
    static final String COL_CREATE_TIME = "create_time";

    HMnoteStorage(Context context) {
        super(context, storageName, null, versionNumber);
    }

    @Override
    public void onUpgrade(SQLiteDatabase noteStorage, int previousVersion, int updatedVersion) {
        if (updatedVersion <= previousVersion) {
            return;
        }
        //delete all of the database
        noteStorage.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(noteStorage);

    }

    @Override
    public void onCreate(SQLiteDatabase noteStorage) {

        //initializing database schema
        String sqlCommand = "CREATE TABLE " + DATABASE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY, " + COL_SUBJECT + " TEXT, " +
                COL_DETAIL + " TEXT, " + COL_CREATE_DATE + " TEXT, " +
                COL_CREATE_TIME + " TEXT )";
        System.out.println("SQLCOMMAND ==== " + sqlCommand);
        noteStorage.execSQL(sqlCommand);
    }


    //creating the note and adding to database
    public long writeNote(HMnoteObject note) {
        SQLiteDatabase noteStorage = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        //adding the note to the database
        row.put(COL_CREATE_DATE, note.getCreateDate());
        row.put(COL_CREATE_TIME, note.getCreateTime());
        row.put(COL_SUBJECT, note.getSubject());
        row.put(COL_DETAIL, note.getDetail());

        long noteid = noteStorage.insert(DATABASE_NAME, null, row);
        System.out.println("writing note ========= " + noteid);
        return noteid;
    }


    //retrieving the note from database
    public HMnoteObject getNoteByID(long noteid) {



        retrieveAllNotes();


        SQLiteDatabase noteStorage = this.getReadableDatabase();
        String[] columns = new String[] {COL_ID, COL_SUBJECT, COL_DETAIL,
                COL_CREATE_DATE, COL_CREATE_TIME};
        Cursor dataBaseCursor = noteStorage.query (
                DATABASE_NAME,
                columns,
                //looking for documents based on this id
                COL_ID + " = ? ",
                new String[] {String.valueOf(noteid)},
                null, null, null, null);
        System.out.println("GETNOTE ===== " + noteid);
        if (dataBaseCursor != null) {
            dataBaseCursor.moveToFirst();
            return new HMnoteObject(
                    Long.parseLong(dataBaseCursor.getString(0)),
                    dataBaseCursor.getString(1),
                    dataBaseCursor.getString(2),
                    dataBaseCursor.getString(3),
                    dataBaseCursor.getString(4)
            );
        }
        return new HMnoteObject();


    }

    //editing existing note
    public int modifyNote(HMnoteObject note) {
        SQLiteDatabase noteStorage = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(COL_SUBJECT, note.getSubject());
        row.put(COL_DETAIL, note.getDetail());
        row.put(COL_CREATE_DATE, note.getCreateDate());
        row.put(COL_CREATE_TIME, note.getCreateTime());

        return noteStorage.update(DATABASE_NAME, row, COL_ID + "=?",
                new String[] {String.valueOf(note.getId())});
    }

    //retrieving all notes from database
    public List<HMnoteObject> retrieveAllNotes() {
        SQLiteDatabase noteStorage = this.getReadableDatabase();
//        noteStorage.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
//        onCreate(noteStorage);


        List<HMnoteObject> totalNotes = new ArrayList<>();
        String SQLcommand = "SELECT * FROM " + DATABASE_NAME + " ORDER BY " + COL_ID+" DESC";

        Cursor dataBaseCursor = noteStorage.rawQuery(SQLcommand, null);

        int position = 1;
        while (dataBaseCursor.move(position)) {
            System.out.println("CURSOR COLUMNS ===== " + dataBaseCursor.getColumnNames().toString());
            HMnoteObject object = new HMnoteObject(
                    Long.parseLong(dataBaseCursor.getString(0)),
                    dataBaseCursor.getString(1),
                    dataBaseCursor.getString(2),
                    dataBaseCursor.getString(3),
                    dataBaseCursor.getString(4)
            );
            totalNotes.add(object);
            position += 1;
        }
        System.out.println("RETRIEVE ALL NOTES ======= " + totalNotes.size());
        return totalNotes;
    }


    //delete the note
    public void removeNote(long noteid) {
        SQLiteDatabase noteStorage = this.getWritableDatabase();
        noteStorage.delete(DATABASE_NAME, COL_ID + "=?",
                new String[] {String.valueOf(noteid)});
        //completing and closing the database
        noteStorage.close();
    }

}
