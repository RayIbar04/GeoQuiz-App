package com.example.geoquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var scoreText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var cheatButton: Button

    private val questions = arrayOf(
        "The capital of France is Paris.",
        "The Nile River is in South America.",
        "Australia is both a country and a continent.",
        "Mount Everest is the tallest mountain above sea level.",
        "Japan is located in Europe.",
        "The Sahara is the largest hot desert in the world."
    )

    private val answers = booleanArrayOf(
        true,
        false,
        true,
        true,
        false,
        true
    )

    private var currentQuestion = 0
    private var score = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionText = findViewById(R.id.questionText)
        scoreText = findViewById(R.id.scoreText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        cheatButton = findViewById(R.id.cheatButton)

        if (savedInstanceState != null) {
            currentQuestion =
                savedInstanceState.getInt("currentQuestion")

            score =
                savedInstanceState.getInt("score")

            answered =
                savedInstanceState.getBoolean("answered")
        }

        showQuestion()
        updateScore()

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            nextQuestion()
        }

        cheatButton.setOnClickListener {
            val intent =
                Intent(this, CheatActivity::class.java)

            intent.putExtra(
                "answer",
                answers[currentQuestion]
            )

            startActivity(intent)
        }
    }

    private fun showQuestion() {
        questionText.text =
            questions[currentQuestion]
    }

    private fun checkAnswer(userAnswer: Boolean) {

        if (answered) {
            Toast.makeText(
                this,
                "You already answered this question.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (userAnswer == answers[currentQuestion]) {

            // Correct answer
            answered = true
            score++

            Toast.makeText(
                this,
                "Correct!",
                Toast.LENGTH_SHORT
            ).show()

            updateScore()

        } else {

            // Incorrect answer
            Toast.makeText(
                this,
                "Incorrect!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun nextQuestion() {

        if (!answered) {

            Toast.makeText(
                this,
                "Please answer the question first.",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (currentQuestion < questions.size - 1) {

            currentQuestion++

            answered = false

            showQuestion()

        } else {

            Toast.makeText(
                this,
                "Quiz complete! Final Score: $score/${questions.size}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun updateScore() {
        scoreText.text =
            "Score: $score/${questions.size}"
    }

    override fun onSaveInstanceState(
        outState: Bundle
    ) {
        super.onSaveInstanceState(outState)

        outState.putInt(
            "currentQuestion",
            currentQuestion
        )

        outState.putInt(
            "score",
            score
        )

        outState.putBoolean(
            "answered",
            answered
        )
    }
}