package rui.todd

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import rui.utils.IBaseMediaPlayer
import rui.view.AcpVideoView


class SurfaceMediaActivity : AppCompatActivity() {
    private lateinit var acp: AcpVideoView
    lateinit var fl: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface_media)
        fl = findViewById(R.id.surface_fl)
        acp = AcpVideoView(this)
        acp.open(fl, 0, 0, -1, -1)
        acp.bind(getExternalFilesDir(Environment.DIRECTORY_MOVIES)?.absolutePath + "/tutu")
        acp.start()
    }
}