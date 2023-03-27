package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edUserName : EditText = findViewById(R.id.LTBFullName)
        val edPassword : EditText = findViewById(R.id.LTBContact)
        val loginButton : Button = findViewById(R.id.LTBBook)
        val newRegister = findViewById<TextView>(R.id.registration)
        val db = Data(applicationContext, "healthcare", null, 1)
        loginButton.setOnClickListener {
            val username = edUserName.text.toString()
            val password = edPassword.text.toString()
            if ((username.isEmpty()) || (password.isEmpty())) {
                Toast.makeText(this, "fill the details!", Toast.LENGTH_SHORT).show()
            }
            else {
                if(db.login(username, password) == 1) {
                    Toast.makeText(this, "login success!", Toast.LENGTH_SHORT).show()
                    val sharedPreference = getSharedPreferences("MY_PRE",Context.MODE_PRIVATE)
                    val editor = sharedPreference.edit()
                    editor.putString("USERNAME", username)
                    editor.apply()
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                else {
                    Toast.makeText(this, "Invalid Username and Password", Toast.LENGTH_SHORT).show()
                }

            }

        }

        newRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}