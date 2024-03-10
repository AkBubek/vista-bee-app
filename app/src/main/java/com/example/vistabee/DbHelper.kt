package com.example.vistabee

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "vistaBeeUsers", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, password TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }


    fun addUser(user:User){
        val values = contentValuesOf()
        values.put("login",user.login)
        values.put("email",user.email)
        values.put("password",user.password)

        val db = this.writableDatabase

        db.insert("users", null, values)

        db.close()
    }

    fun getUser(email: String, password: String) : Boolean{
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT  * FROM users WHERE email = '$email' AND password = '$password'", null)
        return result.moveToFirst()
    }

    @SuppressLint("Range")
    fun getUserNames(): List<String> {
        val userList = mutableListOf<String>()
        val db = this.readableDatabase

        val query = "SELECT login FROM users"
        val cursor = db.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val userName = it.getString(it.getColumnIndex("login"))
                userList.add(userName)
            }
        }

        return userList
    }

}