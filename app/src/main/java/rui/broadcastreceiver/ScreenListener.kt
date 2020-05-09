package rui.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.PowerManager

class ScreenListener {
    var mContext: Context
    private lateinit var mScreenReceiver: ScreenBroadcastReceiver
    public lateinit var mScreenStateListener: ScreenStateListener
    var x: Int = 1

    constructor(context: Context) {
        this.mContext = context
        mScreenReceiver = ScreenBroadcastReceiver()
    }

    inner class ScreenBroadcastReceiver : BroadcastReceiver() {
        private var action: String? = null

        override fun onReceive(context: Context?, intent: Intent?) {
            action = intent?.action
            when (action) {
                Intent.ACTION_SCREEN_OFF -> {
                    mScreenStateListener.onScreenOFF()
                }
                Intent.ACTION_USER_PRESENT -> {
                    mScreenStateListener.onScreenON()
                }
            }
        }
    }

    fun begin(listener: ScreenStateListener) {
        mScreenStateListener = listener
        registerListener()
        getScreenState()
    }

    private fun getScreenState() {
        val manager: PowerManager = mContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (manager.isInteractive) {
            mScreenStateListener.onScreenON()
        } else {
            mScreenStateListener.onScreenOFF()
        }
    }

    interface ScreenStateListener {
        fun onScreenOFF()
        fun onScreenON()
    }

    fun registerListener() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_USER_PRESENT)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        mContext.registerReceiver(mScreenReceiver, filter)
    }

    fun unregisterListener() {
        mContext.unregisterReceiver(mScreenReceiver)
    }
}