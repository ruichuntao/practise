package rui.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger

class MessengerService : Service() {
    companion object {
        const val MSG_SAY_HELLO = 0

        class IncomingHandler(val applicationContext: Context) : Handler() {
            override fun handleMessage(msg: Message) {
                when(msg.what){
                    MSG_SAY_HELLO->{
                        val bundle = msg.data
                        val string = bundle.getString("name")
                    }
                    else->{
                        super.handleMessage(msg)
                    }
                }
                super.handleMessage(msg)

            }
        }
    }

    lateinit var messenger: Messenger

    override fun onBind(intent: Intent): IBinder {
        messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }
}