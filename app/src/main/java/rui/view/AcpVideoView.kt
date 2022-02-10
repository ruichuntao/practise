package rui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import rui.todd.R
import rui.utils.IBaseMediaPlayer
import java.util.*


class AcpVideoView(context: Context) : FrameLayout(context), IBaseMediaPlayer {
    private var mSurface: TextureView
    private lateinit var mSeekBar: SeekBar
    private lateinit var mTimeTv: TextView
    private var mTotTimeTv: TextView
    private var mControlIv: ImageView
    private var mClose: ImageView
    private var mFlSurface: FrameLayout
    private var mControl: Boolean = true // 学生是否有视频的控制权
    private var mPlayer: MediaPlayer = MediaPlayer()

    private val mShowProgress: Runnable = object : Runnable {
        override fun run() {
            setProgress()
            if (isPlaying()) {
                postDelayed(this, 1000)
            }
        }
    }

    private fun setProgress() {
        val message = Message.obtain()
        message.obj = currentPosition()
        mHandler.sendMessage(message)
    }

    private var mHandler = Handler {
        val duration = it.obj as Int
        mSeekBar.progress = duration
        mTimeTv.text = getTimeTxt(duration)
        false
    }

    init {
        View.inflate(context, R.layout.surface_media_layout, this)
        mSurface = findViewById(R.id.surface_view)
        mSeekBar = findViewById(R.id.seek_bar)
        mTimeTv = findViewById(R.id.time)
        mTotTimeTv = findViewById(R.id.tot_time)
        mControlIv = findViewById(R.id.control)
        mClose = findViewById(R.id.close)
        mFlSurface = findViewById(R.id.surface_fl)
        initListener()
    }

    private val TAG = "AcpVideoView"

    private fun getTimeTxt(duration: Int) =
        String.format("%02d", duration / (60 * 60) % 60) + ":" +
                String.format("%02d", duration / 60 % 60) + ":" +
                String.format("%02d", duration % 60)

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        mClose.setOnClickListener {
            if (!mControl) return@setOnClickListener
            close()
        }
        mControlIv.setOnClickListener {
            if (!mControl) return@setOnClickListener
            if (isPlaying()) {
                pause()
            } else {
                start()
            }
        }
        mSeekBar.setOnTouchListener { _, _ -> !mControl }
        mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser && mControl)
                    seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (mControl)
                    pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (mControl)
                    start()
            }
        })
        mSurface.surfaceTextureListener = object : TextureView.SurfaceTextureListener {

            override fun onSurfaceTextureAvailable(p0: SurfaceTexture, p1: Int, p2: Int) {
                mPlayer.setSurface(Surface(p0))
            }

            override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture, p1: Int, p2: Int) {

            }

            override fun onSurfaceTextureDestroyed(p0: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(p0: SurfaceTexture) {

            }

        }
    }

    private fun setDataSource(file: String) {
        mPlayer.setDataSource(file)
        mPlayer.prepare()
    }

    override fun release() {
        removeCallbacks(mShowProgress)
        mPlayer.release()
    }

    override fun start() {
        post(mShowProgress)
        mPlayer.start()
        mControlIv.setImageResource(R.drawable.acp_pause)
    }

    override fun pause() {
        removeCallbacks(mShowProgress)
        mPlayer.pause()
        mControlIv.setImageResource(R.drawable.acp_play)
    }

    override fun isPlaying(): Boolean {
        return mPlayer.isPlaying
    }

    override fun seekTo(sec: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mPlayer.seekTo(sec.toLong() * 1000, MediaPlayer.SEEK_CLOSEST)
        } else {
            mPlayer.seekTo(sec * 1000)
        }
    }


    override fun currentPosition(): Int {
        return mPlayer.currentPosition / 1000
    }

    override fun duration(): Int {
        return mPlayer.duration / 1000
    }

    override fun bind(file: String) {
        createNewPlayer()
        setDataSource(file)
        val duration = duration()
        mSeekBar.max = duration
        mTotTimeTv.text = getTimeTxt(duration)
    }

    private fun createNewPlayer() {
        reset()
        release()
        mPlayer = MediaPlayer()
    }

    override fun close() {
        if (parent == null) return
        (parent as ViewGroup).removeView(this)
        reset()
    }

    override fun setControl(control: Boolean) {
        this.mControl = control
    }

    override fun setCloseVisible(visible: Boolean) {
        mClose.visibility = if (visible) VISIBLE else GONE
    }

    override fun getPlayer(): IBaseMediaPlayer {
        return this
    }

    override fun open(viewGroup: ViewGroup, left: Int, top: Int, width: Int, height: Int) {
        viewGroup.removeView(this)
        val p = MarginLayoutParams(-1, -1)
        p.height = height
        p.width = width
        p.topMargin = top
        p.leftMargin = left
        viewGroup.addView(this, p)
    }

    override fun reset() {
        removeCallbacks(mShowProgress)
        mPlayer.reset()
    }



}