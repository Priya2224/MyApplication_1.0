package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.TextView


class CalculatorActivity : ComponentActivity() {

    private lateinit var displayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondlayout)

        displayTextView = findViewById(R.id.display)

        // Number buttons
        findViewById<Button>(R.id.btn0).setOnClickListener { appendToDisplay("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener { appendToDisplay("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { appendToDisplay("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { appendToDisplay("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { appendToDisplay("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { appendToDisplay("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { appendToDisplay("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { appendToDisplay("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { appendToDisplay("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { appendToDisplay("9") }

        // Operator buttons
        findViewById<Button>(R.id.btnPlus).setOnClickListener { appendToDisplay("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { appendToDisplay("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { appendToDisplay("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { appendToDisplay("/") }

        // Clear button
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearDisplay() }

        // Equals button
        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult()}
    }

    private fun appendToDisplay(value: String) {
        displayTextView.text = displayTextView.text.toString() + value
    }

    private fun clearDisplay() {
        displayTextView.text = ""
    }

    private fun calculateResult() {
        val expression = displayTextView.text.toString()
        try {
            val resultDouble = evaluateExpression(expression)  // evaluate expression to Double

            // Format result: show as Int if whole number, else show decimal
            val result = if (resultDouble % 1.0 == 0.0) {
                resultDouble.toLong().toString()
            } else {
                resultDouble.toString()
            }

            displayTextView.text = result
        } catch (e: Exception) {
            displayTextView.text = "Error"
        }
    }

    private fun evaluateExpression(expr: String): Double {
        return try {
            val tokens = expr.split("(?<=[+\\-*/])|(?=[+\\-*/])".toRegex())
            var result = tokens[0].toDouble()
            var i = 1
            while (i < tokens.size) {
                val op = tokens[i]
                val next = tokens[i + 1].toDouble()
                result = when (op) {
                    "+" -> result + next
                    "-" -> result - next
                    "*" -> result * next
                    "/" -> result / next
                    else -> result
                }
                i += 2
            }
            result
        } catch (e: Exception) {
            0.0
        }
    }

}







