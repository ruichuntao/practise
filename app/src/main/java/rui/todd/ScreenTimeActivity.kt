package rui.todd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_screen_time.*
import rui.broadcastreceiver.ScreenListener
import java.util.*

class ScreenTimeActivity : AppCompatActivity() {
    lateinit var screenListener: ScreenListener
    var time: Long = 0L
    var curTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_time)
        screenListener = ScreenListener(this)
        screenListener.begin(object : ScreenListener.ScreenStateListener {
            override fun onScreenOFF() {
                time += (System.currentTimeMillis() - curTime) / 1000
            }

            override fun onScreenON() {
                time_tv.text = time.toString() + "s"
                curTime = System.currentTimeMillis()
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        screenListener.unregisterListener()
    }

}
