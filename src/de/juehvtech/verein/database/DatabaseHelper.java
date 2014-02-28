package de.juehvtech.verein.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper(Context context) {
        super(context, DatabaseStrings.NAME, null, DatabaseStrings.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseStrings.RESET_STRING);
        db.execSQL(DatabaseStrings.PERSON_CREATION_STRING);
        db.execSQL(DatabaseStrings.TRAININER_CREATION_STRING);
        db.execSQL(DatabaseStrings.EVENT_CREATION_STRING);
        db.execSQL(DatabaseStrings.SHEET_CREATION_STRING);
        db.execSQL(DatabaseStrings.SHEET_PERSON_MAP_CREATION_STRING);
        db.execSQL(DatabaseStrings.EVENT_PERSON_MAP_CREATION_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
