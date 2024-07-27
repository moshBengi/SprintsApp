package com.example.appwithgpt

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import android.widget.TextView

class MainActivity : ComponentActivity() {
    private lateinit var timerTextView: TextView
    private var countDownTimer: CountDownTimer? = null
    private lateinit var timeInput: EditText
    private lateinit var sprintInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startButton: Button = findViewById(R.id.startButton)
        timeInput = findViewById(R.id.timeInput)  // Initialize the EditText
        sprintInput = findViewById(R.id.sprintInput)  // Initialize the EditText

        // Set an OnClickListener
        startButton.setOnClickListener {
            val timeInMinutes = timeInput.text.toString().toIntOrNull() ?: 5  // Use 5 as default if input is invalid or empty
            startTimer(timeInMinutes * 60000L)  // Convert minutes to milliseconds
        }
    }

    private fun startTimer(timeInMillis: Long) {
        countDownTimer?.cancel()  // Cancel any existing timers
        timerTextView = findViewById(R.id.timerTextView)
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Update the TextView to show the remaining seconds
                timerTextView.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // Display that the timer has finished
                timerTextView.text = "Done!"
            }
        }.start()
    }
}
