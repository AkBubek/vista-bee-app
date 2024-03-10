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
<<<<<<< HEAD

=======
//        db!!.execSQL("DROP TABLE IF EXISTS users")
//        onCreate(db)
>>>>>>> 79636d2579b9f48074d12c60fb9a70102227b99e
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

        val result = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", arrayOf(email, password))
        return result.moveToFirst()
    }



    fun getUserName(email: String?): String? {
        if (email.isNullOrEmpty()) {
            return null // Возвращаем null, если email пуст или null
        }

        val db = this.readableDatabase
        var userName: String? = null
        val cursor = db.rawQuery("SELECT login FROM users WHERE email = ?", arrayOf(email))
        cursor.use {
            if (it.moveToFirst()) {
                userName = it.getString(it.getColumnIndex("login"))
            }
        }
        return userName
    }


}