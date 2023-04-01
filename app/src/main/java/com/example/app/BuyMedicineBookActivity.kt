package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BuyMedicineBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine_book)

        val edName : EditText = findViewById(R.id.BMBFullName)
        val edAddress : EditText = findViewById(R.id.BMBAddress)
        val edContact : EditText = findViewById(R.id.BMBContact)
        val edPinCode : EditText = findViewById(R.id.BMBPinCode)
        val btnBooking : Button = findViewById(R.id.BMBBook)
        val intent = intent
        val price = intent.getStringExtra("bookingPrice").toString()
        val date = intent.getStringExtra("date")

        btnBooking.setOnClickListener {
            val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val username = sharedPreference.getString("USERNAME", "").toString()
            val db = Data(applicationContext, "healthcare", null, 1)
            db.addOrder(username, edName.text.toString(), edAddress.text.toString(),edContact.text.toString(),edPinCode.text.toString().toInt(), date.toString(), "", price.toFloat(), "medicine")
            db.removeCart(username, "medicine")
            Toast.makeText(this, "Your booking is done Successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}