package com.example.app

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
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
        var totalAmount : Float = 0F
        val dbData : ArrayList<String> = db.getCartData (username, "lab")
//        Toast.makeText(this,""+dbData, Toast.LENGTH_LONG).show()
        val packages = Array(dbData.size) { arrayOfNulls<String>(5) }
        for (i in 0 until dbData.size) {
            val arrData = dbData[i].toString()
            val strData = arrData.split("\\$".toRegex()).toTypedArray()
            packages[i][0] = strData[0]
            packages[i][4] = "cost : ${strData[1]}/-"
            totalAmount += strData[1].toFloat()
        }

        tvTotal.text = "$totalAmount"
        System.out.println("price"+tvTotal.text.toString())
        for(i in packages.indices) {
            val item = HashMap<String, Any>()
            packages[i][0]?.let { item.put("line1", it) }
            packages[i][1]?.let { item.put("line2", it) }
            packages[i][2]?.let { item.put("line3", it) }
            packages[i][3]?.let { item.put("line4", it) }
            packages[i][4]?.let { item.put("line5", it) }
            list.add(item)
        }
        val sa = SimpleAdapter(this, list, R.layout.multi_lines, arrayOf("line1", "line2", "line3", "line4","line5"), intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))
        val lst : ListView = findViewById(R.id.BMListView)
        lst.adapter = sa

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
        checkout.setOnClickListener {
            val it  = Intent(this, LabTestBookActivity::class.java)
            it.putExtra("bookingPrice", tvTotal.text.toString())
            it.putExtra("date", dateButton.text.toString())
            it.putExtra("time", timeButton.text.toString())
            startActivity(it)


        }
    }
}