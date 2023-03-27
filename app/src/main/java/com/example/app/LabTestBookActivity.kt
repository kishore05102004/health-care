package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LabTestBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test_book)
        val edName : EditText = findViewById(R.id.LTBFullName)
        val edAddress : EditText = findViewById(R.id.LTBAddress)
        val edContact : EditText = findViewById(R.id.LTBContact)
        val edPinCode : EditText = findViewById(R.id.LTBPinCode)
        val btnBooking : Button = findViewById(R.id.LTBBook)

        val intent = Intent()
        val bundle = Bundle()
        System.out.println("price:"+intent.getStringExtra("bookingPrice").toString())
        System.out.println("amount:"+bundle.getString("bookingPrice"))
//        val price = arrayOf(intent.getStringExtra("price").toString().split(":"))
        val price = intent.getStringExtra("bookingPrice").toString()
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")

        btnBooking.setOnClickListener {
            val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val username = sharedPreference.getString("USERNAME", "").toString()
            val db = Data(applicationContext, "healthcare", null, 1)
            db.addOrder(username, edName.text.toString(), edAddress.text.toString(),edContact.text.toString(),edPinCode.text.toString().toInt(), date.toString(), time.toString(), 4684.01, "lab")
            db.removeCart(username, "lab")
            Toast.makeText(this, "Your booking is done Successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}