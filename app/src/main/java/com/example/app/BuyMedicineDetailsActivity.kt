package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BuyMedicineDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine_details)
        val tvPackageName : TextView = findViewById(R.id.BMDPackages)
        val totalCost : TextView = findViewById(R.id.BMDTotalCost)
        val packageDetails : TextView = findViewById(R.id.BMDEditTextMultiLine)
        packageDetails.keyListener = null
        val back : Button = findViewById(R.id.BMDBack)
        val addToCart : Button = findViewById(R.id.BMDAddToCart)
        val intent = intent
        System.out.println("packages details :"+intent.getStringExtra("text2"))
        System.out.println("Total Cost :"+intent.getStringExtra("text3"))
        System.out.println("packages:"+intent.getStringExtra("text1"))

        tvPackageName.text = intent.getStringExtra("text1")
        packageDetails.text = intent.getStringExtra("text2").toString()
        totalCost.text = "Total Cost: "+intent.getStringExtra("text3")+"/-"

        back.setOnClickListener {
            startActivity(Intent(this, BuyMedicineActivity::class.java))
        }
        addToCart.setOnClickListener {
            val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val username = sharedPreference.getString("USERNAME", "").toString()
            val product = tvPackageName.text.toString()
            val price : Float = intent.getStringExtra("text3").toString().toFloat()
            val db = Data(applicationContext, "healthcare", null, 1)
            if(db.checkCart(username, product) == 1){
                Toast.makeText(this, "Product Already Added", Toast.LENGTH_SHORT).show()
            }
            else {
                db.addCart(username, product, price, "medicine")
                Toast.makeText(this, "Record Inserted to Cart", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BuyMedicineActivity::class.java))
            }

        }


    }
}