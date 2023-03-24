package com.example.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class FindDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_doctor)

        val exit = findViewById<CardView>(R.id.cardFDBack)
        val familyPhysician = findViewById<CardView>(R.id.cardFDFamilyPhysician)
        val dietitian = findViewById<CardView>(R.id.cardFDDieticion)
        val dentist = findViewById<CardView>(R.id.cardFDDentist)
        val surgeon = findViewById<CardView>(R.id.cardFDSurgeon)
        val cardiologists = findViewById<CardView>(R.id.cardFDCardiologists)

        exit.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        familyPhysician.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title","Family Physicians")
            startActivity(intent)
        }

        dietitian.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title","Dieticians")
            startActivity(intent)
        }

        dentist.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title","Dentist")
            startActivity(intent)
        }

        surgeon.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title","Surgeon")
            startActivity(intent)
        }

        cardiologists.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title","Cardiologists")
            startActivity(intent)
        }
    }
}