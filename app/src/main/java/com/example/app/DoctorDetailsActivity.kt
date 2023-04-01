package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class DoctorDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        val doctorDetails1 = arrayOf(
            arrayOf("doctor_name: Ajit Saste", "Hospital Address: pimpri", "Exp: 5yrs", "Mobile No: 555-555-5555", "600"),
            arrayOf("doctor_name: prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:  555-555-5555", "900"),
            arrayOf("doctor_name: Ashok Panda", "Hospital Address: Kataraj", "Exp: 7yrs", "Mobile No:  555-555-5555", "800"),
            arrayOf("doctor_name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:  555-555-5555", "500"),
            arrayOf("doctor_name: Swapnil kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:  555-555-5555", "100"),
        )

        val doctorDetails2 = arrayOf(
            arrayOf("doctor_name: Ajit Saste", "Hospital Address: pimpri", "Exp: 5yrs", "Mobile No: 555-555-5555", "600"),
            arrayOf("doctor_name: prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:  555-555-5555", "900"),
            arrayOf("doctor_name: Ashok Panda", "Hospital Address: Kataraj", "Exp: 7yrs", "Mobile No:  555-555-5555", "800"),
            arrayOf("doctor_name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:  555-555-5555", "500"),
            arrayOf("doctor_name: Swapnil kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:  555-555-5555", "100"),
        )

        val doctorDetails3 = arrayOf(
            arrayOf("doctor_name: Ajit Saste", "Hospital Address: pimpri", "Exp: 5yrs", "Mobile No: 555-555-5555", "600"),
            arrayOf("doctor_name: prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:  555-555-5555", "900"),
            arrayOf("doctor_name: Ashok Panda", "Hospital Address: Kataraj", "Exp: 7yrs", "Mobile No:  555-555-5555", "800"),
            arrayOf("doctor_name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:  555-555-5555", "500"),
            arrayOf("doctor_name: Swapnil kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:  555-555-5555", "100"),
        )

        val doctorDetails4 = arrayOf(
            arrayOf("doctor_name: Ajit Saste", "Hospital Address: pimpri", "Exp: 5yrs", "Mobile No: 555-555-5555", "600"),
            arrayOf("doctor_name: prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:  555-555-5555", "900"),
            arrayOf("doctor_name: Ashok Panda", "Hospital Address: Kataraj", "Exp: 7yrs", "Mobile No:  555-555-5555", "800"),
            arrayOf("doctor_name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:  555-555-5555", "500"),
            arrayOf("doctor_name: Swapnil kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:  555-555-5555", "100"),
        )

        val doctorDetails5 = arrayOf(
            arrayOf("doctor_name: Ajit Saste", "Hospital Address: pimpri", "Exp: 5yrs", "Mobile No: 555-555-5555", "600"),
            arrayOf("doctor_name: prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No:  555-555-5555", "900"),
            arrayOf("doctor_name: Ashok Panda", "Hospital Address: Kataraj", "Exp: 7yrs", "Mobile No:  555-555-5555", "800"),
            arrayOf("doctor_name: Deepak Deshmukh", "Hospital Address: Chinchwad", "Exp: 6yrs", "Mobile No:  555-555-5555", "500"),
            arrayOf("doctor_name: Swapnil kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No:  555-555-5555", "100"),
        )

        var doctorDetails = arrayOf<Array<String>>()
        val list=ArrayList<HashMap<String,Any>>()

        val defaultText : TextView = findViewById(R.id.LDLocation)
        val backButton : TextView = findViewById(R.id.BMDBack)
        val intent = intent
        val title = intent.getStringExtra("title")
        defaultText.text = title
        if (title != null) {
            doctorDetails = if(title.compareTo("Family Physicians")==0){
                doctorDetails1
            } else if(title.compareTo("Dieticians")==0){
                doctorDetails2
            } else if(title.compareTo("Dentist")==0){
                doctorDetails3
            } else if(title.compareTo("Surgeon")==0){
                doctorDetails4
            } else {
                doctorDetails5
            }

        }

        backButton.setOnClickListener {
            startActivity(Intent(this, FindDoctorActivity::class.java))
        }

        for (i in doctorDetails.indices) {
            val item = HashMap<String, Any>()
            item["line1"] = doctorDetails[i][0]
            item["line2"] = doctorDetails[i][1]
            item["line3"] = doctorDetails[i][2]
            item["line4"] = doctorDetails[i][3]
            item["line5"] = "cons Fees:"+doctorDetails[i][4]+"/-"
            list.add(item)
        }
        val sa = SimpleAdapter(this, list, R.layout.multi_lines, arrayOf("line1", "line2", "line3", "line4","line5"), intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))
        val lst : ListView = findViewById(R.id.BMListView)
        lst.adapter = sa

        lst.setOnItemClickListener { adapterView, view, i, l ->
            val it : Intent = Intent(this, BookingAppointmentActivity::class.java)
            it.putExtra("text1", title)
            it.putExtra("text2",doctorDetails[i][0])
            it.putExtra("text3",doctorDetails[i][1])
            it.putExtra("text4",doctorDetails[i][3])
            it.putExtra("text5",doctorDetails[i][4])
            startActivity(it)

        }
    }
}