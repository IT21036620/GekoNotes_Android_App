package com.geckolabs.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.geckolabs.notes.NoteModel;

public class TextDB extends SQLiteOpenHelper{

    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "NoteModel";
    private static final String NOTE_ID = "id";
//    private static final String NOTE_TYPE= "type";
    private static final String NOTE_TEXT = "des";

    public TextDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //this is calles the first time a database is accessed. there should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOTE_TEXT + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    public void addNewNote(NoteModel noteModel) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TEXT, noteModel.getText());

         sqLiteDatabase.insert(TABLE_NAME, null, cv);
//        if(insert == -1){
//            return false;
//        }
//        else{
//            return true;
//        }

        sqLiteDatabase.close();
    }

    public String[] getNotes() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + NOTE_TEXT + " FROM " + TABLE_NAME;
        Cursor cur = sqLiteDatabase.rawQuery(query, null);
        String[] note = new String[cur.getCount()];

        for (int i = 0; i < cur.getCount(); i++) {
            cur.move(1);
            note[i] = cur.getString(0);
        }
        return note;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(sqLiteDatabase);
    }
}
