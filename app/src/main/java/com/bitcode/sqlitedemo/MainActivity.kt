package com.bitcode.sqlitedemo

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bitcode.sqlitedemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var db : SQLiteDatabase
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = openOrCreateDatabase(
            "db_products",
            Activity.MODE_PRIVATE,
            null
        )

        try {
            db.execSQL("create table products(_id integer primary key, title text not null, price integer)")
        }
        catch (e : Exception) {}
        //db.execSQL("create table ? (? integer primary key)", arrayOf("users", "uid"))

        //insertRecords()
        getProducts()
        Log.e("tag", "--------------------------------------------------")
        updateRecords()
        getProducts()
        Log.e("tag", "--------------------------------------------------")
        deleteProducts()
        getProducts()
    }

    private fun deleteProducts() {
        var count = db.delete(
            "products",
            "_id in (?, ?)",
            arrayOf("1358764880", "101")
        )
    }

    private fun updateRecords() {
        var values = ContentValues()
        values.put("price", 5555)

        var count = db.update(
            "products",
            values,
            "_id = ?",
            arrayOf("101")
        )

        values = ContentValues()
        values.put("price", 8888)
        values.put("title", "Android Phone")

        db.update(
            "products",
            values,
            "_id = ?",
            arrayOf("1358764880")
        )

    }

    private fun getProducts() {
        //"select col1, col2, col3 from table where price > 1000 and price < 2000 order by price desc"
        /*db.query(
            "products",
            arrayOf("_id", "title", "price"),
            "price > ? and price < ?",
            arrayOf("1000", "2000"),
            "category",
            "sum(price) > 10000",
            "price desc, title"
        )*/

        /*var c : Cursor = db.query(
            "products",
            null,
            null,
            null,
            null,
            null,
            "price desc"
        )*/
        var c = db.rawQuery(
            "select _id, title, price from products where price > ? and price < ?",
            arrayOf("2500", "3000")
        )

        /*c.columnNames
        var colIndexOId = c.getColumnIndex("_id")*/

        while (c.moveToNext()) {
            var id = c.getInt(0)
            var title = c.getString(1)
            var price = c.getInt(2)

            Log.e("tag", "$id $title $price")
        }
        c.close()

    }

    private fun insertRecords() {
        var values = ContentValues()
        values.put("_id", 101)
        values.put("title", "Product 1")
        values.put("price", 1000)

        var rowNum = db.insert("products", null, values)
        if(rowNum >= 0) {
            //success
        }
        else {
            //error
        }
        var random = Random()
        for(i in 1..20) {
            values.put("_id", Math.abs(random.nextInt()))
            values.put("title", "Product $i")
            values.put("price", (Math.abs(random.nextInt()) % 1000) * 3)

            //db.insert("products", "category, stock", values)
            db.insert("products", null, values)
        }
    }
}