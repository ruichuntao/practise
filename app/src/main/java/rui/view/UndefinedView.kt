package rui.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import rui.todd.CircleActivity

class UndefinedView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var mHeight = 0
    var mWidth = 0
    val paint: Paint by lazy { Paint() }
    private val line = 200
    private val radius = 300
    private val duration = 1000L
    private val mValueAnimator: ValueAnimator by lazy { ValueAnimator.ofFloat(0f, 1f).setDuration(duration) }
    private var fraction = 0f
    private val path: Path by lazy { Path() }
    private val pathMeasure: PathMeasure by lazy { PathMeasure(path, false) }
    private val arr: FloatArray = FloatArray(2)
    private val tan: FloatArray = FloatArray(2)


    init {
        paint.apply {
            color = Color.DKGRAY
            style = Paint.Style.FILL_AND_STROKE
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            strokeWidth = 10f
        }
        path.addCircle(0f, 0f, radius.toFloat(), Path.Direction.CCW)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mValueAnimator.cancel()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mValueAnimator.addUpdateListener { animation ->
            fraction = animation.animatedFraction
            postInvalidate()
        }
        mValueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(mWidth / 2.toFloat(), mHeight / 2.toFloat())
        paint.style = Paint.Style.STROKE
        canvas.drawPath(path, paint)
        paint.style = Paint.Style.FILL_AND_STROKE
        pathMeasure.getPosTan(pathMeasure.length * fraction, arr, tan)
        canvas.drawLine(
                0f, 0f,
                arr[0], arr[1], paint
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mHeight = h
        mWidth = w
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

}