package rui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

class FlowLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var itemHorizontalSpacing = 20
    private var itemVerticalSpacing = 20

    private val allLineViews = ArrayList<ArrayList<View>>()
    private val lineHeights = ArrayList<Int>()

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val maxWidth = widthSize
        var lineWidth = 0
        var maxLineWidth = 0
        var lineHeight = 0
        var totalHeight = 0
        val childCount = childCount
        var lineViews = ArrayList<View>()
        var lineCount = 0
        for(i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                val lp = child.layoutParams
                val childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,paddingLeft + paddingRight,lp.width)
                val childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,paddingTop+paddingBottom,lp.height)
                child.measure(childWidthMeasureSpec,childHeightMeasureSpec)
                val childMeasureWidth = child.measuredWidth
                val childMeasureHeight = child.measuredHeight
                val actualItemHorizontalSpacing = if (lineWidth == 0) 0 else itemHorizontalSpacing
                if (lineWidth + actualItemHorizontalSpacing + childMeasureWidth <= maxWidth) {
                    lineWidth += actualItemHorizontalSpacing + childMeasureWidth
                    lineHeight = max(lineHeight, childMeasureHeight)
                    lineViews.add(child)
                } else {
                    maxLineWidth = max(lineWidth,maxLineWidth)
                    lineCount++
                    totalHeight += lineHeight + if (lineCount == 1) 0 else itemVerticalSpacing
                    lineHeights.add(lineHeight)
                    allLineViews.add(lineViews)
                    lineWidth = childMeasureWidth
                    lineHeight = childMeasureHeight
                    lineViews = ArrayList<View>()
                    lineViews.add(child)
                }
            }
        }
        val measuredWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else maxLineWidth
        val measuredHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else totalHeight
        setMeasuredDimension(measuredWidth,measuredHeight)
    }
}