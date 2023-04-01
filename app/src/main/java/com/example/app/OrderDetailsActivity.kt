package com.example.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class OrderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        // initialize the id's
        val lst = findViewById<ListView>(R.id.BMListView)
        val backButton = findViewById<Button>(R.id.BMDBack)
        val list = ArrayList<HashMap<String, Any>>()

        val db = Data(applicationContext, "healthcare", null, 1)
        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val username = sharedPreference.getString("USERNAME", "").toString()
        val orderData : ArrayList<String> = db.getOrderData(username)
        var orderDetails = Array(orderData.size) { arrayOfNulls<String>(5)}
        for ( i in 0 until orderData.size){
            val arrData = orderData[i]
            val strData = arrData.split("\\$".toRegex()).toTypedArray()
            orderDetails[i][0] = strData[0]
            orderDetails[i][1] = strData[1]
            if(strData[7].compareTo("medicine")==0) {
                orderDetails[i][3] = "Del:"+strData[4]
            } else {
                orderDetails[i][3] = "Del:"+strData[4] + " " + strData[5]
            }
            orderDetails[i][2] = "Rs."+strData[6]
            orderDetails[i][4] = strData[7]
        }
        for( i in orderDetails.indices){
            val item = HashMap<String, Any>()
            item["line1"] = orderDetails[i][0] as String
            item["line2"] = orderDetails[i][1] as String
            item["line3"] = orderDetails[i][2] as String
            item["line4"] = orderDetails[i][3] as String
            item["line5"] = orderDetails[i][4] as String
            list.add(item)
        }
        val sa = SimpleAdapter(this, list, R.layout.multi_lines, arrayOf("line1", "line2", "line3", "line4", "line5"), intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))
        lst.adapter = sa


        //set actions to components
        backButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
}