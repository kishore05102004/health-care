package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BuyMedicineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine)
        val packages = arrayOf(
            arrayOf("Uprise-D3 1000IU Capsule", "", "", "","50"),

        )
    }
}