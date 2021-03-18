package rui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import rui.utils.dp2px
import rui.utils.getScreenWidth
import kotlin.math.abs

class SlideEventLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var mPreX: Float = 0f
    var mPreY: Float = 0f
    var mCurX: Float = 0f
    var mCurY: Float = 0f
    var mMoveDistance: Float = 0f

    init {
//        post {
//            val view: View = getChildAt(0)
//            val params = view.layoutParams
//            params.height = ViewGroup.LayoutParams.MATCH_PARENT
//            params.width = getScreenWidth()
//            view.layoutParams = params
//        }
    }

    private val TAG = "SlideEventLayout"

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPreX = event.x
                mPreY = event.y
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                mCurX = event.x
                mCurY = event.y
                val xDiff = abs(mCurX - mPreX)
                val yDiff = abs(mCurY - mPreY)
//                if (yDiff > xDiff) return super.onTouchEvent(event)
                mMoveDistance += mCurX - mPreX
                mPreX = mCurX
                offsetLeftAndRight(mMoveDistance.toInt())
            }
            MotionEvent.ACTION_UP -> {
//                if (abs(mMoveDistance) > (45).dp2px()) {
//                    offsetLeftAndRight(0)
//                }
                mMoveDistance = 0f
            }
            else -> {

            }
        }
        return super.onTouchEvent(event)
    }
}