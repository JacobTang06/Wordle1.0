package com.example.wordle10

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge


class InfoScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.info_screen)

        val backButton = findViewById<ImageButton>(R.id.backButton)

        backButton.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(i)
        }
    }

}