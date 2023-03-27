package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val username = sharedPreference.getString("USERNAME", "").toString()
        Toast.makeText(this, "Hello $username", Toast.LENGTH_SHORT).show()

        val exit = findViewById<CardView>(R.id.cardExit)
        val findDoctor = findViewById<CardView>(R.id.cardFindDoctor)
        val labTest = findViewById<CardView>(R.id.cardLabTest)
        val orderDetails = findViewById<CardView>(R.id.cardOrderDetails)
        val buyMedicine = findViewById<CardView>(R.id.cardBuyMedicine)
        exit.setOnClickListener {
            val editor = sharedPreference.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findDoctor.setOnClickListener {
            startActivity(Intent(this, FindDoctorActivity::class.java))
        }

        labTest.setOnClickListener {
            startActivity(Intent(this, LabTestActivity::class.java))
        }

        orderDetails.setOnClickListener {
            startActivity(Intent(this, OrderDetailsActivity::class.java))
        }
        buyMedicine.setOnClickListener {
            startActivity(Intent(this, BuyMedicineActivity::class.java))
        }

    }
}