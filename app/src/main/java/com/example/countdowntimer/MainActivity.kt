package com.example.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.countdowntimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //this value may be received from a an api
    private val timeReceived = 1641367800000

    private val currentTime = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStartTimer.setOnClickListener {
            val timerTime = timeReceived - currentTime
            if (timerTime > 0) {
                val countDownTimer = object : CountDownTimer(timerTime, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        getFormattedTime(millisUntilFinished)
                    }

                    override fun onFinish() {
                        binding.tvTimerDays.text = "Completed"
                        binding.tvTimerHours.text = "Completed"
                        binding.tvTimerMinutes.text = "Completed"
                        binding.tvTimerSeconds.text = "Completed"
                    }
                }
                countDownTimer.start()
            } else {
                Toast.makeText(this, "Invalid Time", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFormattedTime(millis: Long) {

        val totalSeconds = millis / 1000
        val seconds: Int = (totalSeconds % 60).toInt()
        val totalMinutes = millis / (1000 * 60)
        val minutes: Int = (totalMinutes % 60).toInt()
        val totalHours = millis / (1000 * 60 * 60)
        val hours: Int = (totalHours % 24).toInt()

        /**
         * daysLeft : hoursLeft : minutesLeft : secondsLeft
         * */

        val totalDays = (millis / (1000 * 60 * 60 * 24)).toInt()
        binding.tvTimerDays.text = "${totalDays}d : ${hours}h : ${minutes}m : ${seconds}s"

        /**
         * hoursLeft : minutesLeft : secondsLeft
         * */
        binding.tvTimerHours.text = "${totalHours}h : ${minutes}m : ${seconds}s"

        /**
         * minutesLeft : secondsLeft
         * */
        binding.tvTimerMinutes.text = "${totalMinutes}m : ${seconds}s"

        /**
         * secondsLeft
         * */
        binding.tvTimerSeconds.text = "${totalSeconds}s"

    }
}
