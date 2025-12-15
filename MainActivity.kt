package com.wireddev.fourops

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        divideBtn.setOnClickListener { setOperation("÷") }
        percentageBtn.setOnClickListener { setOperation("%") }

        equalBtn.setOnClickListener { calculateResult() }
        clearBtn.setOnClickListener { clearCalculator() }


        backSpaceBtn.setOnClickListener { deleteNum()}
    }

    private fun deleteNum() {
        if ( resultTextView.text.isNotEmpty() &&  resultTextView.text != "0.0" &&  resultTextView.text != "Error") {
            resultTextView.text = resultTextView.text.dropLast( 1)
        } else {
            // Toast.makeText(this, "Invalid Operation", Toast.LENGTH_SHORT).show()
            val toast = Toast(this)
            val view: TextView = TextView(this).apply {
                text = "Invalid Operation"
                setTextColor(Color.CYAN)
                setBackgroundColor(Color.BLACK)
                setPadding(32, 16, 32, 16)
            }
            toast.view = view
            toast.show()
        }
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
        try {
            val secondNumber = resultTextView.text.toString().toDouble()

            val result: Double = when (operator) {
                "+" -> firstNum + secondNumber
                "-" -> firstNum - secondNumber
                "*" -> firstNum * secondNumber
                "÷" -> firstNum / secondNumber
                else -> secondNumber
            }

            previousCalculationTextView.text = "$firstNum $operator $secondNumber"
            resultTextView.text = result.toString()
            isNewOperation = true
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }

    private fun clearCalculator() {
       resultTextView.text = "0.0"
        previousCalculationTextView.text = ""
        firstNum = 0.0
        isNewOperation = true
        operator = ""
    }
}

/*
The Android Activity lifecycle is a set of states an Activity can be in throughout its entire
lifetime, from the time it's created to when it's destroyed. The system calls a series of lifecycle
methods to move the activity between these states. As a developer, you can override these methods to
perform work at different stages of the activity's life.

Here are the core lifecycle methods in the order they are typically called:

*   `onCreate()`: This is called when the activity is first created. You should perform all your
essential setup in this method, such as setting the layout, initializing views, and binding data.

*   `onStart()`: Called when the activity becomes visible to the user.

*   `onResume()`: Called when the activity is in the foreground and the user can interact with it.

*   `onPause()`: Called when another activity comes into the foreground. Your activity is still visible in the background, but has lost
 focus. You should stop any ongoing operations that consume CPU or battery in this method.

*   `onStop()`: Called when the activity is no longer visible to the user, either because a new
activity is covering it completely or the activity is being destroyed.

*   `onRestart()`: Called when an activity is about to be started again after it has been stopped.

*   `onDestroy()`: This is the final callback before the activity is destroyed. You should use this
to release any resources that were acquired in `onCreate()`
.
*/