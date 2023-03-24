package com.example.app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Data(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase) {
        val query = "CREATE TABLE $TABLE_NAME ($USERNAME_COL TEXT, $PASSWORD_COL TEXT, $EMAIL_COL TEXT)"
        p0.execSQL(query)
        val cartQuery = "CREATE TABLE $TABLE_NAME_CART($USERNAME_COL TEXT, $PRODUCT_COL TEXT, $PRICE_COL FLOAT,$ORDER_TYPE_COL TEXT) "
        p0.execSQL(cartQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun register(username: String, password: String, email: String) {
        val cv = ContentValues()
        cv.put(USERNAME_COL, username)
        cv.put(PASSWORD_COL, password)
        cv.put(EMAIL_COL, email)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, cv)
        db.close()
    }

    fun login(username: String, password: String) :Int {
        var result = 0
//        val str : Array<String> = arrayOf(username, password)
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $USERNAME_COL ='$username' AND $PASSWORD_COL ='$password'", null)
        if(cursor.moveToFirst()) {
            cursor.close()
            result = 1
        }
        cursor.close()
        return result
    }

    fun addCart(username : String, product : String, price : Float, orderType : String){
        val cv = ContentValues()
        cv.put(USERNAME_COL, username)
        cv.put(PRODUCT_COL, product)
        cv.put(PRICE_COL, price)
        cv.put(ORDER_TYPE_COL, orderType)
        val db = this.writableDatabase
        db.insert(TABLE_NAME_CART, null, cv)
        db.close()
    }

    fun checkCart(username : String, product: String) : Int {
        var result = 0
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME_CART WHERE $USERNAME_COL = '$username' AND $PRODUCT_COL = '$product'", null)
        if(cursor.moveToFirst()) {
            cursor.close()
            result = 1
        }
        cursor.close()
        return result
    }

    fun removeCart(username : String, orderType: String){
        val db = this.writableDatabase
        db.delete("$TABLE_NAME_CART", "$USERNAME_COL = $username AND $ORDER_TYPE_COL = $orderType", null)
        db.close()
    }

    fun getCartData(username: String, orderType: String) : ArrayList<String> {
        lateinit var arr : ArrayList<String>
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME_CART WHERE $USERNAME_COL = $username AND $ORDER_TYPE_COL = $orderType", null)
        if(cursor.moveToFirst()){
            do{
                val products = cursor.getString(1)
                val price = cursor.getString(2)
                arr.add("$products$$price")
            }while(cursor.moveToNext())
        }
        db.close()
        return arr
    }
    companion object {
        const val TABLE_NAME = "users"
        const val USERNAME_COL = "username"
        const val PASSWORD_COL = "password"
        const val EMAIL_COL = "email"
        const val TABLE_NAME_CART = "cart"
        const val PRODUCT_COL = "product"
        const val PRICE_COL = "price"
        const val ORDER_TYPE_COL = "orderType"

    }
}