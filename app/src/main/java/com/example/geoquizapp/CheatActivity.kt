package com.example.geoquizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cheat)

        val cheatText =
            findViewById<TextView>(R.id.cheatText)

        val backButton =
            findViewById<Button>(R.id.backButton)

        val answer =
            intent.getBooleanExtra("answer", false)

        if (answer) {

            cheatText.text =
                "The answer is TRUE"

        } else {

            cheatText.text =
                "The answer is FALSE"
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}