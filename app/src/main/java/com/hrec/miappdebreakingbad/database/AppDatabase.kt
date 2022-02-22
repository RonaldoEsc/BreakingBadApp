package com.hrec.miappdebreakingbad.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    override fun onCreate(bd: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " +
                TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_ID_CHARACTER + " INTEGER" +
                ")")
        bd?.execSQL(createTable)
    }

    override fun onUpgrade(bd: SQLiteDatabase?, p1: Int, p2: Int) {
        bd?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(bd)
    }

    companion object{
        const val DATABASE_NAME = "Favorites"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "Favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_ID_CHARACTER = "idCharacter"

    }
}