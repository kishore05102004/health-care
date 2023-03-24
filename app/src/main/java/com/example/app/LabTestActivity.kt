package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class LabTestActivity : AppCompatActivity() {
    private val packages = arrayOf(
        arrayOf("Package 1 : Full Body Checkup", "", "", "", "999"),
        arrayOf("Package 2 : Blood Glucose Fasting", "", "", "", "299"),
        arrayOf("Package 3 : COVID - 19 Antibody -IgG", "", "", "", "899"),
        arrayOf("Package 4 : Thyroid Check", "", "", "", "499"),
        arrayOf("Package 5 : Immunity Check", "", "", "", "699")

    )
    val packagesDetail = arrayOf(
        "Blood Glucose Fasting\n" +
            "Complete Hemogram\n" +
            "HbA1c\n" +
            "Iron Studies\n" +
            "Kidney Function Test\n" +
            "LDH Lactate Dehydrogenase, Serum\n" +
            "Lipid Profile\n" +
            "Liver Function Test",
        "Blood Glucose Fasting",
        "COVID - 19 Antibody -IgG",
        "Thyroid Profile-Total (T3, T4, & TSH Ultra-Sensitive)",
        "Complete Hemogram\n"+
                "CRP (C Reactive Protein) Quantitative, Serum\n" +
                "Iron Studies\n" +
                "Kidney Function Test\n" +
                "Vitamin D Total-25 Hydroxy\n" +
                "Liver Function Test\n" +
                "Lipid Profile"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)
        val goCart = findViewById<Button>(R.id.goToCart)
        val back = findViewById<Button>(R.id.buttonLDBack)
        val listView = findViewById<ListView>(R.id.listViewCart)
        val list=ArrayList<HashMap<String,Any>>()

        back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        for(i in packages.indices) {
            val item = HashMap<String, Any>()
            item["line1"] = packages[i][0]
            item["line2"] = packages[i][1]
            item["line3"] = packages[i][2]
            item["line4"] = packages[i][3]
            item["line5"] = "Total Cost:"+packages[i][4]+"/-"
            list.add(item)
        }
        val sa = SimpleAdapter(this, list, R.layout.multi_lines, arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a,R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e ) )
        listView.adapter = sa

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val it : Intent = Intent(this, LabTestDetailsActivity::class.java)
            it.putExtra("text1", packages[i][0])
            it.putExtra("text2", packagesDetail[i])
            it.putExtra("text3", packages[i][4])
            startActivity(it);
        }

        goCart.setOnClickListener {
            startActivity(Intent(this, CartLabActivity::class.java))
        }
    }
}