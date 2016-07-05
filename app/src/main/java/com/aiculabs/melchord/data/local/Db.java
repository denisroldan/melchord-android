package com.aiculabs.melchord.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.aiculabs.melchord.data.model.Tab;

public class Db {

    public Db() { }

    public abstract static class MelchordTabTable {
        public static final String TABLE_NAME = "melchord_tab";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_CONTENT = "date_of_birth";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_TITLE + " TEXT NOT NULL, " +
                        COLUMN_TYPE + " TEXT NOT NULL, " +
                        COLUMN_URL + " TEXT NOT NULL, " +
                        COLUMN_CONTENT + " TEXT NOT NULL, " +
                " ); ";

        public static ContentValues toContentValues(Tab tab) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, tab.getId());
            values.put(COLUMN_TITLE, tab.getTitle());
            values.put(COLUMN_TYPE, tab.getType());
            values.put(COLUMN_URL, tab.getUrl());
            values.put(COLUMN_CONTENT, tab.getContent());
            return values;
        }

        public static Tab parseCursor(Cursor cursor) {
            Tab tab = new Tab();
            tab.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            tab.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
            tab.setType(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)));
            tab.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL)));
            tab.setContent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)));
            return tab;
        }
    }
}
