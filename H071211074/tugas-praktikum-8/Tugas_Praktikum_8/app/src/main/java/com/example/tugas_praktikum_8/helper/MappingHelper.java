package com.example.tugas_praktikum_8.helper;

import android.database.Cursor;

import com.example.tugas_praktikum_8.data.db.DatabaseContract;
import com.example.tugas_praktikum_8.data.model.Note;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Note> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE));

            notes.add(new Note(id, title, description, date));
        }
        return notes;
    }
}