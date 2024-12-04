package com.example.l04

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.l04.Utilities.Constants
import com.example.l04.Utilities.TimeFormatter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var main_LBL_time: MaterialTextView
    private lateinit var main_FAB_play: ExtendedFloatingActionButton
    private lateinit var main_BTN_stop: ExtendedFloatingActionButton

    val handler: Handler = Handler(Looper.getMainLooper())
    private var startTime: Long = 0
    private var timerOn: Boolean = false


    val runnable: Runnable = object : Runnable {
        override fun run() {
            //reschedule
            handler.postDelayed(this, Constants.Timer.DELAY)
            //refresh UI
            updateTimerUI()
        }
    }

    private fun updateTimerUI() {
        val currentTime = System.currentTimeMillis()
        main_LBL_time.text = TimeFormatter.formatTime(currentTime - startTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        initViews()
    }

    private fun findViews() {
        main_LBL_time = findViewById(R.id.main_LBL_time)
        main_FAB_play = findViewById(R.id.main_FAB_play)
        main_BTN_stop = findViewById(R.id.main_BTN_stop)
    }

    private fun initViews() {
        main_FAB_play.setOnClickListener{ v -> startTimer()}
        main_BTN_stop.setOnClickListener{ v -> stopTimer()}
    }

    private fun startTimer() {
        if(!timerOn){
            timerOn = true
            startTime = System.currentTimeMillis()
            handler.postDelayed(runnable, Constants.Timer.DELAY)
        }
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable)
        timerOn = false
    }
}
