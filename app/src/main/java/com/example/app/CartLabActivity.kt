package com.example.app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CartLabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_lab)
        // Initialisation
        val tvTotal : TextView = findViewById(R.id.CartTotalPrice)
        val dateButton : Button = findViewById(R.id.cartDate)
        val timeButton : Button = findViewById(R.id.cartTime)
        val checkout : Button = findViewById(R.id.cartCheckout)
        val back : Button = findViewById(R.id.cartBack)
        val list = ArrayList<HashMap<String, Any>>()
        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val username = sharedPreference.getString("USERNAME", "").toString()

        // Date Dialer Setup
        val calendar  = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // minimum date
        val mCalendar = Calendar.getInstance()
        mCalendar.set(2023, Calendar.MARCH, 1 )
        val minDate = mCalendar.timeInMillis

        val datePicker = DatePickerDialog(
            this,
            { _,year,monthOfYear,dayOfMonth ->
                val selectedDate ="${dayOfMonth+1}/$monthOfYear/$year"
                dateButton.text = selectedDate
            },
            year, month, day
        )
        // define minimum date
        datePicker.datePicker.minDate = minDate

        // time
        val timePicker = TimePickerDialog(this, { _,hourOfDay, minute ->
            val selectedTime = "${hourOfDay}:$minute"
            timeButton.text = selectedTime
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)

        val db = Data(applicationContext, "healthcare", null, 1)
        val totalAmount = 0
        val dbData : ArrayList<String> = db.getCartData(username, "lab")
        System.out.println(dbData)
//        Toast.makeText(this,""+dbData, Toast.LENGTH_LONG).show()
        //button actions
        back.setOnClickListener {
            startActivity(Intent(this, LabTestActivity::class.java))
        }

        dateButton.setOnClickListener {
            datePicker.show()
        }
        timeButton.setOnClickListener {
            timePicker.show()
        }
    }
}