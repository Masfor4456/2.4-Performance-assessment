package com.example.a24pa

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

/*
    Mason Ford
    05/25/2026
*/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temperatureInput = findViewById<EditText>(R.id.temperatureInput)
        val conversionGroup = findViewById<RadioGroup>(R.id.conversionGroup)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val infoText = findViewById<TextView>(R.id.infoText)

        convertButton.setOnClickListener {
            val inputText = temperatureInput.text.toString().trim()

            if (inputText.isEmpty()) {
                Toast.makeText(this, "Please enter a temperature value.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val inputValue = inputText.toDoubleOrNull()
            if (inputValue == null) {
                Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedId = conversionGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please choose a conversion direction.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when (selectedId) {
                R.id.radioFtoC -> {
                    if (inputValue < -100 || inputValue > 250) {
                        Toast.makeText(this, "Fahrenheit must be between -100 and 250.", Toast.LENGTH_SHORT).show()
                        resultText.text = ""
                        return@setOnClickListener
                    }

                    val celsius = (inputValue - 32) * 5 / 9
                    resultText.text = String.format(Locale.US, "%.2f °C", celsius)
                    infoText.text = "Converted from Fahrenheit to Celsius"
                }

                R.id.radioCtoF -> {
                    if (inputValue < -75 || inputValue > 125) {
                        Toast.makeText(this, "Celsius must be between -75 and 125.", Toast.LENGTH_SHORT).show()
                        resultText.text = ""
                        return@setOnClickListener
                    }

                    val fahrenheit = (inputValue * 9 / 5) + 32
                    resultText.text = String.format(Locale.US, "%.2f °F", fahrenheit)
                    infoText.text = "Converted from Celsius to Fahrenheit"
                }
            }
        }
    }
}
