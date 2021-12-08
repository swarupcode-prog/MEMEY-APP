package com.example.mememy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val share = findViewById<Button>(R.id.shareButton)
        share.setOnClickListener {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
        }

        val next = findViewById<Button>(R.id.nextButton)
        next.setOnClickListener {
            Toast.makeText(this, "next", Toast.LENGTH_SHORT).show()
        }
    }
}