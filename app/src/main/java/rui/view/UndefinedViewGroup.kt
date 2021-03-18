package rui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class UndefinedViewGroup @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measuredHeight = MeasureSpec.getSize(heightMeasureSpec)
        val measuredWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measuredHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        var height = 0
        var width = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            height += child.measuredHeight
            width = max(width, child.measuredWidth)
        }
        setMeasuredDimension(
                if (measuredWidthMode == MeasureSpec.EXACTLY) measuredWidth
                else width,
                if (measuredHeightMode == MeasureSpec.EXACTLY) measuredHeight
                else height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.layout(0, top, child.measuredWidth, child.measuredHeight)
            top += child.measuredHeight
        }
    }
}