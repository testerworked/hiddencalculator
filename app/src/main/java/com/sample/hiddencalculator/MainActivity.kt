package com.sample.hiddencalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sample.hiddencalculator.SecondActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textInfoTV: TextView
    private lateinit var transferDataB: Button
    private var getResult: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textInfoTV = findViewById(R.id.textInfoTV)
        transferDataB = findViewById(R.id.transferDataB)

        transferDataB.setOnClickListener {
            if(textInfoTV.text.isEmpty()) return@setOnClickListener
            val intent = Intent(this, SecondActivity::class.java)
            launchSomeActivity.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private val launchSomeActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == RESULT_OK){
            val data = result.data
            getResult = data!!.getStringExtra("result").toString()
            textInfoTV.text = "$getResult"
            Toast.makeText(this,"$result", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Cancelled", Toast.LENGTH_LONG).show()
        }

    }
}