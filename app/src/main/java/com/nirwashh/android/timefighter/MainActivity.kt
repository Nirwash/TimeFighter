package com.nirwashh.android.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.nirwashh.android.timefighter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private var score = 0
    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private var initialCountDown: Long = 60000
    private var countDownInterval: Long = 1000
    private var timeLeft = 60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.btnTapMe.setOnClickListener { incrementScore() }
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeft = savedInstanceState.getInt(TIME_LEFT_KEY)
            restoreGame()
        } else {
        resetGame()
        }
    }

    private fun restoreGame() {
        val restoredScore = getString(R.string.your_score, score)
        b.tvGameScore.text = restoredScore
        val restoredTime = getString(R.string.time_left, timeLeft)
        b.tvTimeLeft.text = restoredTime
        countDownTimer = object : CountDownTimer((timeLeft * 1000).toLong(), countDownInterval) {
            override fun onTick(p0: Long) {
                timeLeft = p0.toInt() / 1000
                val timeLeftString = getString(R.string.time_left, timeLeft)
                b.tvTimeLeft.text = timeLeftString
            }

            override fun onFinish() {
               endGame()
            }
        }
        countDownTimer.start()
        gameStarted = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCORE_KEY, score)
        outState.putInt(TIME_LEFT_KEY, timeLeft)
        countDownTimer.cancel()
    }

    private fun incrementScore(){
        if (!gameStarted) {
            startGame()
        }
        score++
        val newScore = getString(R.string.your_score, score)
        b.tvGameScore.text = newScore
    }

    private fun resetGame() {
        score = 0
        val initialScore = getString(R.string.your_score, score)
        b.tvGameScore.text = initialScore

        val initialTileLeft = getString(R.string.time_left, 60)
        b.tvTimeLeft.text = initialTileLeft

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(p0: Long) {
                timeLeft = p0.toInt()/1000
                val timeLeftString = getString(R.string.time_left, timeLeft)
                b.tvTimeLeft.text = timeLeftString
            }

            override fun onFinish() {
               endGame()
            }
        }
        gameStarted = false
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.game_over_message, score), Toast.LENGTH_SHORT).show()
        resetGame()
    }

    companion object {
        private const val SCORE_KEY = "score key"
        private const val TIME_LEFT_KEY = "time left key"
    }

}