package rui.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import rui.utils.dp2px
import java.util.*

class SwipeLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var mIsAnit = false
    private var mTimer: Timer? = null
    private var mValueAnimator: ValueAnimator? = null
    private var mCurIndex = 0
    private var fraction = 0f
    private val mWatchTime = 2000L
    private val mDuration = 500L

    companion object {
        private const val TAG = "SwipeLayout"

    }


    init {
        for (i in 0..10) {
            val v = TextView(context)
            addView(v)
            v.also {
                it.text = i.toString()
                val params = it.layoutParams as LayoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.height = 50.dp2px().toInt()
                params.gravity = Gravity.CENTER
                it.layoutParams = params
                it.setTextColor(Color.BLUE)
                it.textSize = 30f
            }
        }

    }

    private fun checkAniStart() {
        if (childCount > 1 && !mIsAnit) startTime()
    }

    private fun startTime() {
        mTimer?.cancel()
        mTimer?.purge()
        mTimer = Timer()
        mTimer!!.schedule(object : TimerTask() {
            override fun run() {
                post { startAgni() }
            }
        }, mWatchTime, mDuration + mWatchTime)
        mIsAnit = true
    }

    private fun startAgni() {
        mValueAnimator?.end()
        mValueAnimator?.cancel()
        mValueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(mDuration)
        mValueAnimator?.addUpdateListener { animation ->
            fraction = animation.animatedFraction
            Log.e(TAG, "startAgni: $fraction")
            requestLayout()
        }
        mValueAnimator?.start()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        checkAniStart()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mTimer?.cancel()
        mTimer?.purge()
        mValueAnimator?.end()
        mValueAnimator?.cancel()
        mIsAnit = false
    }

    override fun addView(child: View?) {
        super.addView(child)
        checkAniStart()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (childCount <= 1) {
            super.onLayout(changed, l, t, r, b)
            return
        }
        val curView = getChildAt(mCurIndex)
        val curParams = curView.layoutParams as LayoutParams
        val nextView = getChildAt(next())
        val nxtParams = nextView.layoutParams as LayoutParams
        val curTop = (curParams.topMargin + paddingTop - (curParams.topMargin + paddingTop + curParams.bottomMargin + curView.measuredHeight) * fraction).toInt()
        curView.layout(paddingLeft + curParams.leftMargin, curTop, paddingLeft + curParams.leftMargin + curView.measuredWidth, curTop + curView.measuredHeight)
        val nextTop = curTop + curView.measuredHeight + curParams.bottomMargin + paddingTop + nxtParams.topMargin
        nextView.layout(paddingLeft + nxtParams.leftMargin, nextTop, paddingLeft + nxtParams.leftMargin + nextView.measuredWidth, nextTop + measuredHeight)
        if (fraction == 1f) mCurIndex = next()
    }

    private fun next(): Int {
        var cur = mCurIndex
        cur++
        return if (cur == childCount) 0
        else cur
    }
}