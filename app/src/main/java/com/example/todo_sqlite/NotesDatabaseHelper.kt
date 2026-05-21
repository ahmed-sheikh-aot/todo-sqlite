package com.example.todo_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_TITLE TEXT,$COLUMN_DESCRIPTION TEXT)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        val query = "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.execSQL(query)
        onCreate(p0)
    }

    companion object {
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DESCRIPTION = "description"
    }

    fun insertNote(notes: NoteModel) {
        var db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,notes.title)
            put(COLUMN_DESCRIPTION,notes.description)
        }
        db.insert(TABLE_NAME,null,values)
        db?.close()
    }

    fun getAllNotes():List<NoteModel> {
        val notesList = mutableListOf<NoteModel>()
        val db = readableDatabase
        val query  = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))

            notesList.add(NoteModel(id,title,description))
        }
        cursor.close()
        db.close()
        return notesList
    }

}