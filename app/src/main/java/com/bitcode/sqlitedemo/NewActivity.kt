package com.bitcode.sqlitedemo

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {

    //private lateinit var db : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //var dbUtil = DBUtil(this)
        var dbUtil = DBUtil.getInstance(this)

        dbUtil.addCustomer(
            Customer(11, "AA", 1234)
        )
        dbUtil.addCustomer(
            Customer(22, "BB", 5678)
        )

        var customers = dbUtil.getAllCustomers()
        for(cust in customers) {
            Log.e("tag", cust.toString())
        }

       /* db  = ProductsDBHelper(
            this,
            "db_customers",
            null,
            1
        ).writableDatabase*/
    }
}