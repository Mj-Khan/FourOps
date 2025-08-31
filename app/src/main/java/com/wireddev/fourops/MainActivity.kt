package com.wireddev.fourops

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNum = 0.0
    private var operator = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTextView = findViewById<TextView>(R.id.resultTextView)
        previousCalculationTextView = findViewById<TextView>(R.id.previousCalculationTextView)

        val btnZero: Button = findViewById<Button>(R.id.btn)
        val btnOne: Button = findViewById<Button>(R.id.btn1)
        val btnTwo: Button = findViewById<Button>(R.id.btn2)
        val btnThree: Button = findViewById<Button>(R.id.btn3)
        val btnFour: Button = findViewById<Button>(R.id.btn4)
        val btnFive: Button = findViewById<Button>(R.id.btn5)
        val btnSix: Button = findViewById<Button>(R.id.btn6)
        val btnSeven: Button = findViewById<Button>(R.id.btn7)
        val btnEight: Button = findViewById<Button>(R.id.btn8)
        val btnNine: Button = findViewById<Button>(R.id.btn9)

        val addBtn: Button = findViewById<Button>(R.id.btnAdd)
        val minusBtn: Button = findViewById<Button>(R.id.btnMinus)
        val multiplyBtn: Button = findViewById<Button>(R.id.btnMultiply)
        val divideBtn: Button = findViewById<Button>(R.id.btnDivide)
        val decimalBtn: Button = findViewById<Button>(R.id.btnDot)
        val percentageBtn: Button = findViewById<Button>(R.id.btnPercent)

        val clearBtn: Button = findViewById<Button>(R.id.btnClear)
        val equalBtn: Button = findViewById<Button>(R.id.btnEquals)
        val backSpaceBtn: Button = findViewById<Button>(R.id.btnBackspace)

        btnZero.setOnClickListener { appendNumber("0") }
        btnOne.setOnClickListener { appendNumber("1") }
        btnTwo.setOnClickListener { appendNumber("2") }
        btnThree.setOnClickListener { appendNumber("3") }
        btnFour.setOnClickListener { appendNumber("4") }
        btnFive.setOnClickListener { appendNumber("5") }
        btnSix.setOnClickListener { appendNumber("6") }
        btnSeven.setOnClickListener { appendNumber("7") }
        btnEight.setOnClickListener { appendNumber("8") }
        btnNine.setOnClickListener { appendNumber("9") }
        decimalBtn.setOnClickListener { appendNumber(".") }


        // Operator button click listeners
        addBtn.setOnClickListener { setOperation("+") }
        minusBtn.setOnClickListener { setOperation("-") }
        multiplyBtn.setOnClickListener { setOperation("*") }
        divideBtn.setOnClickListener { setOperation("") }
        equalBtn.setOnClickListener { calculateResult() }
        clearBtn.setOnClickListener { clearCalculator() }
    }

    private fun appendNumber(number: String) {
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else {
            resultTextView.text = "${resultTextView.text}$number"
            // resultTextView.append(number)
        }
    }

    private fun setOperation(operator: String) {
        firstNum = resultTextView.text.toString().toDouble()
        this.operator = operator
        isNewOperation = true
        previousCalculationTextView.text = "$firstNum ${this.operator}"
    }

    private fun calculateResult() {
        //TODO: Add logic to show calculation results
    }

    private fun clearCalculator() {
        //TODO: Add logic for clearing the calculation
    }
}