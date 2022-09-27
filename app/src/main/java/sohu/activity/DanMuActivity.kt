package sohu.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import rui.todd.R
import sohu.danmuku.DanMuHelper
import sohu.danmuku.DanMuView
import sohu.danmuku.model.DanmakuEntity

class DanMuActivity : AppCompatActivity() {

    private lateinit var mDanMuContainer: DanMuView
    private lateinit var mDanMuHelper: DanMuHelper
    private var isHide = false

    private var mButton: Button? = null
    var isPlay = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dan_mu)
        mDanMuHelper = DanMuHelper(this)

        // 全站弹幕（广播）
        mDanMuContainer = findViewById<DanMuView>(R.id.danmaku_container_broadcast)
        mDanMuContainer.prepare()
        mDanMuHelper.add(mDanMuContainer)

        val list = ArrayList<DanmakuEntity>()
        for (i in 0..20) {
            val danmakuEntity = DanmakuEntity()
            danmakuEntity.text = "滚滚长江东逝水，浪花淘尽英雄~~"
            danmakuEntity.index = i % 2
            list.add(danmakuEntity)
        }
        mDanMuHelper.initDanMu(list)

        var i = 0
        var index = 0
        findViewById<View>(R.id.button).setOnClickListener {
            val danmakuEntity = DanmakuEntity()
            danmakuEntity.text = "滚滚长江东逝水，浪花淘尽英雄~~" + i++
            danmakuEntity.isMine = true
            danmakuEntity.index = index
            index = (index + 1) % 2
            addRoomDanmaku(danmakuEntity)
        }
        mButton = findViewById<View>(R.id.button3) as Button
        mButton!!.setOnClickListener {
            isHide = !isHide
            mButton!!.text = if (isHide) "显示弹幕" else "隐藏弹幕"
            hideAllDanMuView(isHide)
        }
        findViewById<View>(R.id.button4).setOnClickListener {
            if (isPlay) mDanMuContainer.stop() else mDanMuContainer.play()
            isPlay = !isPlay
            (findViewById<View>(R.id.button4) as Button).text =
                if (isPlay) "暂停弹幕" else "播放弹幕"
        }
    }


    /**
     * 发送一条房间内的弹幕
     */
    private fun addRoomDanmaku(danmakuEntity: DanmakuEntity) {
        mDanMuHelper.addDanMu(danmakuEntity, null)
    }

    /**
     * 显示或者隐藏弹幕
     *
     * @param hide
     */
    private fun hideAllDanMuView(hide: Boolean) {
        mDanMuContainer.alpha = if (hide) 0f else 1f
    }

    override fun onDestroy() {
        mDanMuHelper.release()
        super.onDestroy()
    }

}