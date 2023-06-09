package com.example.app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookingAppointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_appointment)
        val tv : TextView = findViewById(R.id.TextViewLTBTitle)
        val edName : EditText = findViewById(R.id.BMBFullName)
        val edAddress : EditText = findViewById(R.id.BMBAddress)
        val edContactNumber : EditText = findViewById(R.id.BMBPinCode)
        val edFees : EditText = findViewById(R.id.BMBContact)
        var timeButton : Button = findViewById(R.id.cartTime)
        var dateButton : Button = findViewById(R.id.BMCartDate)
        val buttonBack : Button = findViewById(R.id.BMBBook)
        val buttonBook : Button = findViewById(R.id.BookingAppointment)

        // Date Dialer setup
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        // setting up minimum calendar

        val mCalendar = Calendar.getInstance()
        mCalendar.set(2023, Calendar.MARCH, 1)
        val minDate = mCalendar.timeInMillis

        // make the user not to edit the input fields
        edName.keyListener = null
        edAddress.keyListener = null
        edContactNumber.keyListener = null
        edFees.keyListener =null

        val it : Intent = intent
        val title = it.getStringExtra("text1")
        val fullName  = it.getStringExtra("text2")
        val address = it.getStringExtra("text3")
        val contact  = it.getStringExtra("text4")
        val fees = it.getStringExtra("text5")

        tv.text = title
        edName.setText(fullName)
        edAddress.setText(address)
        edContactNumber.setText(contact)
        edFees.setText("Cons Fees:$fees/-")
        // date
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

        dateButton.setOnClickListener {
            datePicker.show()
        }

        timeButton.setOnClickListener {
            timePicker.show()
        }

        buttonBack.setOnClickListener {
            startActivity(Intent(this, FindDoctorActivity::class.java))
        }
        buttonBook.setOnClickListener {
            val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
            val username = sharedPreference.getString("USERNAME", "").toString()
            val db = Data(applicationContext, "healthcare", null, 1)
            if(db.checkAppointmentExists(username, "$title => $fullName",address.toString(),contact.toString(),dateButton.text.toString(),timeButton.text.toString() ) ==1 ) {
                Toast.makeText(this, "Appointment already booked", Toast.LENGTH_LONG).show()
            }
            else {
                db.addOrder(username, "$title => $fullName",address.toString(),contact.toString(), 0,dateButton.text.toString(),timeButton.text.toString(), fees.toString().toFloat(), "appointment")
                Toast.makeText(this, "Your appointment is Done Sucessfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }
}