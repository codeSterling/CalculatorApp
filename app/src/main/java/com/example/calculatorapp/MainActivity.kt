package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val etInput1 = findViewById<TextInputEditText>(R.id.eTInput1)
        val etInput2 = findViewById<TextInputEditText>(R.id.eTInput2)
        val tvResult = findViewById<TextView>(R.id.tVResult)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnMul = findViewById<Button>(R.id.btnMul)

        btnPlus.setOnClickListener {
            calculateResult(etInput1, etInput2, tvResult) { a, b -> a + b }
        }
        btnMinus.setOnClickListener {
            calculateResult(etInput1, etInput2, tvResult) { a, b -> a - b }
        }
        btnDivide.setOnClickListener {
            calculateResult(etInput1, etInput2, tvResult) { a, b ->
                if (b != 0) {
                    a / b
                } else {
                    
                    showErrorMessage("Cannot divide by zero")
                    0
                }
            }
        }
        btnMul.setOnClickListener {
            calculateResult(etInput1, etInput2, tvResult) { a, b -> a * b }
        }
    }

    private fun calculateResult(
        etInput1: TextInputEditText,
        etInput2: TextInputEditText,
        tvResult: TextView,
        operation: (Int, Int) -> Int
    ) {
        try {
            val input1 = etInput1.text.toString().toInt()
            val input2 = etInput2.text.toString().toInt()

            val result = operation(input1, input2)
            tvResult.text = result.toString()
        } catch (e: NumberFormatException) {

            showErrorMessage("Invalid input. Please enter valid numbers.")
        }
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}