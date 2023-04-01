package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HealthArticlesDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_articles_details)
        val text : TextView = findViewById(R.id.HADTitle)
        val button : Button =  findViewById(R.id.HADBack)
        val image : ImageView = findViewById(R.id.HADImageView)
        val intent = intent
        text.text = intent.getStringExtra("text1")
        var bundle : Bundle? = intent.extras
        var resId = bundle!!.getInt("text2")
        image.setImageResource(resId)
        button.setOnClickListener {
            startActivity(Intent(this, HealthArticlesActivity::class.java))
        }
    }
}