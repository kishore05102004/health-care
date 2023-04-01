package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class HealthArticlesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_articles)
        val healthArticles = arrayOf(
            arrayOf("Walking Daily", "", "", "", "Click More Details"),
            arrayOf("Home Care of COVID - 19", "", "", "", "Click More Details"),
            arrayOf("Stop Smoking", "", "", "", "Click More Details"),
            arrayOf("Menstrual Cramps", "", "", "", "Click More Details"),
            arrayOf("Healthy Gut", "", "", "", "Click More Details")
        )

        val images = arrayOf(
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
        )
        val backButton : Button = findViewById(R.id.HABack)
        val lst : ListView = findViewById(R.id.HAListView)
        backButton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        val list=ArrayList<HashMap<String,Any>>()
        for(i in healthArticles.indices){
            val item = HashMap<String, Any>()
            item["line1"] = healthArticles[i][0]
            item["line2"] = healthArticles[i][1]
            item["line3"] = healthArticles[i][2]
            item["line4"] = healthArticles[i][3]
            item["line5"] = healthArticles[i][4]
            list.add(item)
        }

        val sa = SimpleAdapter(this, list, R.layout.multi_lines, arrayOf("line1", "line2", "line3", "line4", "line5"), intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))
        lst.adapter = sa
        
        lst.setOnItemClickListener{ AdapterView: AdapterView<*>, view: View, i: Int, j: Long ->
            val it = Intent(this, HealthArticlesDetailsActivity::class.java)
            it.putExtra("text1", healthArticles[i][0])
            it.putExtra("text2", images[i])
            startActivity(it)
        }
    }
}