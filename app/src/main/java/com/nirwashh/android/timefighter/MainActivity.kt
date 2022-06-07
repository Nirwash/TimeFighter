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
        resetGame()
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
}