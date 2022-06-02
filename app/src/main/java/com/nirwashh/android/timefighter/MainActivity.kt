package com.nirwashh.android.timefighter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nirwashh.android.timefighter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        b.btnTapMe.setOnClickListener { incrementScore() }
    }

    private fun incrementScore(){
        score++
        val newScore = getString(R.string.your_score, score)
        b.tvGameScore.text = newScore
    }

    private fun resetGame() {}

    private fun startGame() {}

    private fun endGame() {}
}