package com.sample.hiddencalculator

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var firstET : EditText
    private lateinit var secondET : EditText
    private lateinit var resultB : Button
    private lateinit var addB : Button
    private lateinit var subtractB : Button
    private lateinit var multiplyB : Button
    private lateinit var divideB : Button


    private var num1: Double = 0.0
    private var num2: Double = 0.0
    private var operation: String = ""
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        firstET = findViewById(R.id.firstET)
        secondET = findViewById(R.id.secondET)
        resultB = findViewById(R.id.resultB)

        addB = findViewById(R.id.addB)
        subtractB = findViewById(R.id.subtractB)
        multiplyB = findViewById(R.id.multiplyB)
        divideB = findViewById(R.id.divideB)

        addB.setOnClickListener { operation = "+" }
        subtractB.setOnClickListener { operation = "-" }
        multiplyB.setOnClickListener { operation = "*" }
        divideB.setOnClickListener { operation = "/" }

        resultB.setOnClickListener {
            num1 = firstET.text.toString().toDouble()
            num2 = secondET.text.toString().toDouble()
            when (operation) {
                "+" -> {
                    result = num1 + num2
                }
                "-" -> {
                    result = num1 - num2
                }
                "*" -> {
                    result = num1 * num2
                }
                "/" -> {
                    result = num1 / num2
                }
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("result", result.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }




}