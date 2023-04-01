package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class BuyMedicineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine)
        val packages = arrayOf(
            arrayOf("Uprise-D3 1000IU Capsule", "", "", "","50"),
            arrayOf("HealthVit Chromium Picolinate 200mcg Capsule", "", "", "", "305"),
            arrayOf("Vitamin B Complex Capsules", "", "", "", "448"),
            arrayOf("Dolo 650 Tablet", "", "", "", "30"),
            arrayOf("Crocin 650 Advance tablet","", "","", "50"),
            arrayOf("Strepsils Medicated Lozenges for Sore Throat", "", "", "", "40"),
            arrayOf("Tata Img Calcium + Vitamin D3", "", "", "", "30"),
            arrayOf("Feronia -XT Tablet", "", "", "", "130")
        )
        val packageDetails = arrayOf(
            "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pains\n"+
                    "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulate blood glucose.",
            "Provides relief from vitamin B deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n" +
                    "It helps reduce skin blemish and pigmentation.\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messengers responsible for fever and pain.",
            "Helps relieve fever and bring down a high temperature\n" +
                    "Suitable for people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                    "Provides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or Low intake of iron"
        )

        val lst : ListView = findViewById(R.id.BMListView)
        val btnBack : Button = findViewById(R.id.BMDBack)
        val btnGoToCart : Button = findViewById(R.id.BMGoToCart)
        val list=ArrayList<HashMap<String,Any>>()

        btnBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        btnGoToCart.setOnClickListener {
            startActivity(Intent(this, CartBuyMedicineActivity::class.java))
        }
        for (i in packages.indices) {
            val item = HashMap<String,Any>()
            item["line1"] = packages[i][0]
            item["line2"] = packages[i][1]
            item["line3"] = packages[i][2]
            item["line4"] = packages[i][3]
            item["line5"] = "Total Cost: "+packages[i][4]+"/-"
            list.add(item)
        }
        val sa = SimpleAdapter(this, list, R.layout.multi_lines, arrayOf("line1", "line2", "line3", "line4","line5"), intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e))
        lst.adapter = sa

        lst.setOnItemClickListener { adapterView, view, i, j, ->
            val intent = Intent(this, BuyMedicineDetailsActivity::class.java)
            intent.putExtra("text1", packages[i][0])
            intent.putExtra("text2", packageDetails[i])
            intent.putExtra("text3", packages[i][4])
            startActivity(intent)
        }

    }
}