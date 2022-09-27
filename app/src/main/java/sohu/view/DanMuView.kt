package sohu.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.TextView


class DanMuView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val danMuList = ArrayList<String>()
    val size = 50
    var cur = 0
    private val views = arrayOfNulls<TextView>(size)
    private var screenWidth = 0
    private val runnable = object : Runnable {
        override fun run() {
            if (cur >= size) {
                removeCallbacks(this)
                return
            }
            mHandler.sendEmptyMessage(0)
            postDelayed(this, 1000)
        }
    }
    private val mHandler = Handler(Looper.getMainLooper()) {
        val danMu = getDanMu()
        danMu.text = danMuList[cur++]
        danMu.setTextColor(Color.parseColor("#000000"))
//        danMu.setBackgroundColor(Color.parseColor("#000000"))
        val animator = ObjectAnimator.ofFloat(
            danMu,
            "translationX",
            screenWidth.toFloat(),
            -danMu.width.toFloat()
        )
        animator.interpolator = LinearInterpolator()
        animator.duration = 2000
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                if (danMu.parent != null) (danMu.parent as FrameLayout).removeView(danMu)
                addView(danMu, LayoutParams(-2, -2))
            }

            override fun onAnimationEnd(animation: Animator?) {
                danMu.text = ""
                removeView(danMu)
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

        })
        animator.start()
        false
    }


    init {
        screenWidth = (context as Activity).windowManager.defaultDisplay.width
        for (i in 0 until size) {
            views[i] = TextView(context)
        }
        for (i in 0..size) {
            danMuList.add("$i     你好你好哈哈哈")
        }
    }

    fun getDanMu(): TextView {
        for (i in 0 until size) {
            if (views[i]!!.text.isBlank()) return views[i]!!
        }
        return TextView(context)
    }

    fun start() {
        post(runnable)
    }

}