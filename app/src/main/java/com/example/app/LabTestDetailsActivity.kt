package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LabTestDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test_details)
        val cost : TextView = findViewById(R.id.LDtotalCost)
        val packages : TextView = findViewById(R.id.LDPackages)
        val details : TextView = findViewById(R.id.editTextLDMultiLine)
        val btnAddToCart : Button = findViewById(R.id.LDaddToCart)
        val btnBack : Button = findViewById(R.id.BMBack)
        val db = Data(applicationContext, "healthcare", null, 1)
        // details not be edited by user.
        details.keyListener = null

        val intent = intent
        packages.text = intent.getStringExtra("text1")
        details.text = intent.getStringExtra("text2")
        cost.text = "Total Cost : "+intent.getStringExtra("text3")+"/-"

        btnBack.setOnClickListener {
            startActivity(Intent(this, LabTestActivity::class.java))
        }

        btnAddToCart.setOnClickListener {
            val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val username = sharedPreference.getString("USERNAME", "").toString()
            val product = packages.text.toString()
            val price : Float = intent.getStringExtra("text3").toString().toFloat()
            if(db.checkCart(username, product) == 1){
                Toast.makeText(this, "Product Already Added", Toast.LENGTH_SHORT).show()
            }
            else {
                db.addCart(username, product, price, "lab")
                Toast.makeText(this, "Record Inserted to Cart", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LabTestActivity::class.java))

            }
        }
    }
}