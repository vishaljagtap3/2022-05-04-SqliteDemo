package com.bitcode.sqlitedemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductsDBHelper(
    context: Context,
    dbName : String,
    cursorFactory: SQLiteDatabase.CursorFactory?,
    version : Int
) : SQLiteOpenHelper(context, dbName, cursorFactory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table customers(_id integer primary key, name text, phone integer)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion : Int, newVersion : Int) {
        db!!.execSQL("create table borrowings(_id integer primary key, customer_id integer references customers(_id))")
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}