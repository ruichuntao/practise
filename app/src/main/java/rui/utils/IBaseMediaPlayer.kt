package rui.utils

import android.content.res.AssetFileDescriptor
import android.view.SurfaceHolder
import android.view.ViewGroup
import java.io.File

interface IBaseMediaPlayer {
    fun release() // 释放
    fun start() // 开始
    fun pause() // 暂停
    fun isPlaying(): Boolean // 判断正在播放
    fun seekTo(sec: Int) // 设置播放进度
    fun currentPosition(): Int // 当前进度
    fun duration(): Int  // 总时长
    fun bind(file: String)  // 设置数据源
    fun close() // 关闭播放器
    fun open(viewGroup: ViewGroup, left: Int, top: Int, width: Int, height: Int) // 打开播放器
    fun setControl(control: Boolean) // 设置学生控制权
    fun setCloseVisible(visible: Boolean) // 设置关闭按钮是否可见
    fun getPlayer(): IBaseMediaPlayer // 获取播放器
    fun reset() // 重置播放器
}