package com.levento.sfrapp.common.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.levento.sfrapp.R

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val layout = findViewById<ConstraintLayout>(R.id.cardLayout)
        val orgNumberText = findViewById<TextView>(R.id.text_orgnumber)
        val companyNameText = findViewById<TextView>(R.id.text_company_name)
        val expirationDateText = findViewById<TextView>(R.id.text_valid_date)

        val extras = intent.extras

        if (extras != null) {
            val orgNumber = extras.getString("orgNumber")
            val companyName = extras.getString("companyName")
            val expirationDate = extras.getString("expirationDate")

            orgNumberText.text = orgNumber ?: "Missing org Number"
            companyNameText.text = companyName ?: "Missing company name"
            expirationDateText.text = expirationDate ?: "Missing expiration date"
        }

        layout.setOnClickListener {
            finish()
        }
    }
}