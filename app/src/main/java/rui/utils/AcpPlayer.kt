package rui.utils

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.SurfaceHolder

//class AcpPlayer : IBaseMediaPlayer {
//
//    private val player: MediaPlayer by lazy { MediaPlayer() }
//
//    override fun release() {
//        player.release()
//    }
//
//    override fun start() {
//        player.start()
//    }
//
//    override fun pause() {
//        player.pause()
//    }
//
//    override fun isPlaying(): Boolean {
//        return player.isPlaying
//    }
//
//    override fun setDataSource(file: String) {
//        player.setDataSource(file)
//        val attrBuilder = AudioAttributes.Builder()
//        attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC)
//        player.setAudioAttributes(attrBuilder.build())
//        player.prepare()
//    }
//
//    override fun setDataSource(file: AssetFileDescriptor) {
//        player.setDataSource(file)
//        val attrBuilder = AudioAttributes.Builder()
//        attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC)
//        player.setAudioAttributes(attrBuilder.build())
//        player.prepare()
//
//    }
//
//    override fun seekTo(sec: Int) {
//        player.seekTo(sec)
//    }
//
//    override fun currentPosition(): Int {
//        return player.currentPosition
//    }
//
//    override fun duration(): Int {
//        return player.duration
//    }
//
//}