package it.diunito.dialogdemo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy

import java.util.Timer
import java.util.TimerTask

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : RobotActivity() {

    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.OVERLAY)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                goToCategories()
            }
        }, 1500)
    }

    override fun onPause() {
        timer?.cancel()
        super.onPause()
    }

    private fun goToCategories() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
}
