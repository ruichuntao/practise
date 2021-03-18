package rui.todd

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_circular_reveal.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class CircularRevealActivity : AppCompatActivity() {
    private lateinit var fullscreenContent: TextView
    private lateinit var fullscreenContentControls: LinearLayout
    private val hideHandler = Handler()

    private var isFullscreen: Boolean = false
    private val TAG = "CircularRevealActivity"
    private var isShow = false


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_circular_reveal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        isFullscreen = true

        // Set up the user interaction to manually show or hide the system UI.
        fullscreenContent = findViewById(R.id.fullscreen_content)
        fullscreenContent.setOnClickListener {
            Log.e(TAG, "onCreate: kotlin" )
        }
//        fullscreenContent.setOnTouchListener { _, event ->
//            if (isShow) {
//                return@setOnTouchListener true
//            }
//            show(event)
//            true
//        }
//        fullscreen_content1.setOnTouchListener { _, event ->
//            if (isShow) {
//                return@setOnTouchListener true
//            }
//            hide(event)
//            true
//        }

        fullscreenContentControls = findViewById(R.id.fullscreen_content_controls)

    }

    private fun hide(event: MotionEvent) {
        val start = fullscreen_content1.height.toFloat()
        val end = 0f
        val location = IntArray(2)
        location[0] = event.x.toInt()
        location[1] = event.y.toInt()
        val anim = ViewAnimationUtils.createCircularReveal(fullscreen_content1,
                location[0],
                location[1],
                start,
                end
        )
        anim.duration = 400
        anim.interpolator = LinearInterpolator()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                fullscreen_content1.visibility = View.VISIBLE
                isShow = true
            }

            override fun onAnimationEnd(animation: Animator?) {
                fullscreen_content1.visibility = View.INVISIBLE
                isShow = false
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

        })
        anim.start()
    }

    private fun show(event: MotionEvent) {
        val startRadius: Float = 0f
        val endRadius: Float = fullscreen_content1.height.toFloat()
        val location = IntArray(2)
        location[0] = event.x.toInt()
        location[1] = event.y.toInt()
        val anim = ViewAnimationUtils.createCircularReveal(fullscreen_content1,
                location[0],
                location[1],
                startRadius,
                endRadius)
        anim.duration = 400
        anim.interpolator = LinearInterpolator()
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                fullscreen_content1.visibility = View.VISIBLE
                isShow = true
            }

            override fun onAnimationEnd(animation: Animator?) {
                fullscreen_content1.visibility = View.VISIBLE
                isShow = false
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
        anim.start()
    }


    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}