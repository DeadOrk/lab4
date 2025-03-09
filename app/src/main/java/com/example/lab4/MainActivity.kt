package com.example.lab4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button


    private lateinit var questionTextView: TextView
    private lateinit var resultTextView: TextView

    /*private var answersArray:  BooleanArray? = BooleanArray(questionBank.size)
    {
        false
    }*/

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider = ViewModelProvider(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        /*if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex")
            answersArray = savedInstanceState.getBooleanArray("answersArray")
        }*/

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener {

            checkAnswer(true)
            answerVisiblility(true)

        }

        falseButton.setOnClickListener {

            checkAnswer(false)
            answerVisiblility(true)

        }

        nextButton.setOnClickListener {

           //currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
            answerVisiblility(false)

        }

        /*backButton.setOnClickListener {

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

        }*/

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,  quizViewModel.currentIndex)
        //outState.putBooleanArray("answersArray", answersArray)

    }

    /*override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)
        currentIndex = savedInstanceState.getInt("currentIndex")
        answersArray = savedInstanceState.getBooleanArray("answersArray")


    }*/

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

        //val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)


    }

    private fun checkAnswer(userAnswer: Boolean) {

        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId : Int

        if (userAnswer == correctAnswer)
        {
            messageResId = R.string.correct_toast
            //answersArray?.set(currentIndex, true)
        }
        else
        {
            messageResId = R.string.incorrect_toast
            //answersArray?.set(currentIndex, false)
        }

        Toast.makeText(this, messageResId,
            Toast.LENGTH_SHORT)
            .show()

        //showAnswersStats()
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

    /*private fun showAnswersStats() {

        val total = questionBank.size
        val correct = answersArray?.count { it == true }
        val resultText = "Правильных ответов: $correct/$total"

        resultTextView = findViewById(R.id.result_text_view)
        resultTextView.text = resultText

    }*/

}