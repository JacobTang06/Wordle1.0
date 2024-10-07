package com.example.wordle10

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.util.Calendar

class MainActivity : ComponentActivity() {
    private var wordToGuess = ""
    private lateinit var timer : CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var counter = 0

        requestWord {
            val actualWord = findViewById<TextView>(R.id.actualWord)
            actualWord.text = wordToGuess
            Log.d("Actual word", actualWord.text.toString())
            Log.d("Word to guess", wordToGuess)

            val guessButton = findViewById<Button>(R.id.guessingButton)
            val infoButton = findViewById<ImageButton>(R.id.infoButton)


            val guessOne1 = findViewById<TextView>(R.id.guessOne1)
            val guessOne2 = findViewById<TextView>(R.id.guessOne2)
            val guessOne3 = findViewById<TextView>(R.id.guessOne3)
            val guessOne4 = findViewById<TextView>(R.id.guessOne4)
            val guessOne5 = findViewById<TextView>(R.id.guessOne5)

            val guessTwo1 = findViewById<TextView>(R.id.guessTwo1)
            val guessTwo2 = findViewById<TextView>(R.id.guessTwo2)
            val guessTwo3 = findViewById<TextView>(R.id.guessTwo3)
            val guessTwo4 = findViewById<TextView>(R.id.guessTwo4)
            val guessTwo5 = findViewById<TextView>(R.id.guessTwo5)

            val guessThree1 = findViewById<TextView>(R.id.guessThree1)
            val guessThree2 = findViewById<TextView>(R.id.guessThree2)
            val guessThree3 = findViewById<TextView>(R.id.guessThree3)
            val guessThree4 = findViewById<TextView>(R.id.guessThree4)
            val guessThree5 = findViewById<TextView>(R.id.guessThree5)

            val guessFour1 = findViewById<TextView>(R.id.guessFour1)
            val guessFour2 = findViewById<TextView>(R.id.guessFour2)
            val guessFour3 = findViewById<TextView>(R.id.guessFour3)
            val guessFour4 = findViewById<TextView>(R.id.guessFour4)
            val guessFour5 = findViewById<TextView>(R.id.guessFour5)

            val guessFive1 = findViewById<TextView>(R.id.guessFive1)
            val guessFive2 = findViewById<TextView>(R.id.guessFive2)
            val guessFive3 = findViewById<TextView>(R.id.guessFive3)
            val guessFive4 = findViewById<TextView>(R.id.guessFive4)
            val guessFive5 = findViewById<TextView>(R.id.guessFive5)

            val guessedWord = findViewById<EditText>(R.id.guessingTextBox)

            val countdownTimer = findViewById<TextView>(R.id.timerCountdown)

            guessButton.setOnClickListener {
                it.hideKeyboard()
                val guessedWordValue = guessedWord.text.toString().uppercase()

                if (guessedWordValue.length != 5) {
                    guessedWord.error = "Please enter a 5 letter word"
                    return@setOnClickListener
                }

                when (counter) {
                    0 -> {
                        val guessOneCharacters = listOf(guessOne1, guessOne2, guessOne3, guessOne4, guessOne5)
                        checkGuess(guessedWordValue, guessOneCharacters)
                        var i = 0;
                        for(letter in guessedWordValue) {
                            guessOneCharacters[i].text = letter.toString()
                            i++
                        }
                    }

                    1 -> {
                        val guessTwoCharacters = listOf(guessTwo1, guessTwo2, guessTwo3, guessTwo4, guessTwo5)
                        checkGuess(guessedWordValue, guessTwoCharacters)
                        var i = 0;
                        for(letter in guessedWordValue) {
                            guessTwoCharacters[i].text = letter.toString()
                            i++
                        }
                    }

                    2 -> {
                        val guessThreeCharacters = listOf(guessThree1, guessThree2, guessThree3, guessThree4, guessThree5)
                        checkGuess(guessedWordValue, guessThreeCharacters)
                        var i = 0;
                        for(letter in guessedWordValue) {
                            guessThreeCharacters[i].text = letter.toString()
                            i++
                        }
                    }

                    3 -> {
                        val guessFourCharacters = listOf(guessFour1, guessFour2, guessFour3, guessFour4, guessFour5)
                        checkGuess(guessedWordValue, guessFourCharacters)
                        var i = 0;
                        for(letter in guessedWordValue) {
                            guessFourCharacters[i].text = letter.toString()
                            i++
                        }
                    }

                    4 -> {
                        val guessFiveCharacters = listOf(guessFive1, guessFive2, guessFive3, guessFive4, guessFive5)
                        checkGuess(guessedWordValue, guessFiveCharacters)
                        var i = 0;
                        for(letter in guessedWordValue) {
                            guessFiveCharacters[i].text = letter.toString()
                            i++
                        }
                    }
                }
                //Log.d("Actual Word", wordToGuess)
                counter++;


                if (guessedWordValue == actualWord.text && counter <= 5) {
                    Toast.makeText(this, "Congratulations, you won!", Toast.LENGTH_SHORT).show()
                    counter = 0;
                    actualWord.visibility = View.VISIBLE
                    guessButton.visibility = View.INVISIBLE
                    guessedWord.visibility = View.INVISIBLE
                    countdownTimer.visibility = View.VISIBLE

                    val timeTillNextDay = getMidnightTime() - getCurrentTime()
                    timer = object : CountDownTimer(timeTillNextDay, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            val hoursLeft = (millisUntilFinished / (1000 * 60 * 60)).toInt()
                            val minutesLeft = (millisUntilFinished / (1000 * 60) % 60).toInt()
                            val secondsLeft = (millisUntilFinished / 1000 % 60).toInt()
                            countdownTimer.text = "Time left until new word:\n" + String.format("%02d hours, %02d minutes, %02d seconds", hoursLeft, minutesLeft, secondsLeft)
                        }

                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "Timer has completed! Feel free to guess new word!", Toast.LENGTH_LONG).show()
                            restartApp()
                        }
                    }.start()
                } else if (counter >= 5) {
                    Toast.makeText(this, "You failed to guess the correct word!", Toast.LENGTH_SHORT).show()
                    counter = 0;
                    actualWord.visibility = View.VISIBLE
                    guessButton.visibility = View.INVISIBLE
                    guessedWord.visibility = View.INVISIBLE
                    countdownTimer.visibility = View.VISIBLE

                    val timeTillNextDay = getMidnightTime() - getCurrentTime()
                    timer = object : CountDownTimer(timeTillNextDay, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            val hoursLeft = (millisUntilFinished / (1000 * 60 * 60)).toInt()
                            val minutesLeft = (millisUntilFinished / (1000 * 60) % 60).toInt()
                            val secondsLeft = (millisUntilFinished / 1000 % 60).toInt()
                            countdownTimer.text = "Time left until new word:\n" + String.format("%02d hours, %02d minutes, %02d seconds", hoursLeft, minutesLeft, secondsLeft)
                        }

                        override fun onFinish() {
                            Toast.makeText(this@MainActivity, "Timer has completed! Feel free to guess new word!", Toast.LENGTH_LONG).show()
                            restartApp()
                        }
                    }.start()
                }

            }

            infoButton.setOnClickListener {
                infoButtonClicked()
            }
        }


    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordBoxes : List<TextView>) {
        for (i in 0..4) {
            if (guess[i] == wordToGuess[i]) {
                wordBoxes[i].setBackgroundResource(R.color.green)
                wordBoxes[i].setTextColor(resources.getColor(R.color.white))
            } else if (guess[i] in wordToGuess) {
                wordBoxes[i].setBackgroundResource(R.color.yellow)
                wordBoxes[i].setTextColor(resources.getColor(R.color.white))
            } else {
                wordBoxes[i].setBackgroundResource(R.color.grey)
                wordBoxes[i].setTextColor(resources.getColor(R.color.white))
            }

        }
    }

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun infoButtonClicked() {
        val i = Intent(this, InfoScreen::class.java)
        startActivity(i)
    }

    private fun requestWord(callback: () -> Unit) {
        FiveLetterWordList.getRandomFiveLetterWord { word ->
            if (word != null) {
                wordToGuess = word.uppercase()
                callback()
            }
        }
    }

    private fun getCurrentTime() : Long {
        val currentTime = System.currentTimeMillis()
        return currentTime
    }

    private fun getMidnightTime() : Long {
        val nextTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 24)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return nextTime.timeInMillis
    }

    private fun restartApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
