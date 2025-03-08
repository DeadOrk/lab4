package com.example.lab4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import kotlin.math.abs

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button


    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))


    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex")
        }

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener { view: View ->

            checkAnswer(true)
            answerVisiblility(true)

        }

        falseButton.setOnClickListener { view: View ->

            checkAnswer(false)
            answerVisiblility(true)

        }

        nextButton.setOnClickListener {

            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            answerVisiblility(false)

        }

        backButton.setOnClickListener {

            if (currentIndex - 1 < 0)
            {
                currentIndex = questionBank.size - 1
            }
            else
            {
                currentIndex = (currentIndex - 1) % questionBank.size
            }

            updateQuestion()
            answerVisiblility(false)

        }

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", currentIndex);

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)
        currentIndex = savedInstanceState.getInt("currentIndex");

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {

        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)


    }

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = questionBank[currentIndex].answer
        val messageResId : Int
        if (userAnswer == correctAnswer)
        {
            messageResId = R.string.correct_toast
        }
        else
        {
            messageResId = R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId,
            Toast.LENGTH_SHORT)
            .show()

    }

    private fun answerVisiblility(isAnswer: Boolean){

        if (isAnswer){

            trueButton.visibility = View.GONE
            falseButton.visibility = View.GONE

        }
        else
        {
            trueButton.visibility = View.VISIBLE
            falseButton.visibility = View.VISIBLE
        }

    }

}