package rui.aspect

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import rui.todd.BuildConfig


@Aspect
class AspectUtil {
    companion object {
        private const val TAG = "AspectUtil"
    }

    private val SPACE_TIME = 500 //2次点击的间隔时间，单位ms

    private var lastClickTime: Long = 0


    private fun isDoubleClick(): Boolean {
        val currentTime = System.currentTimeMillis()
        Log.e(TAG, "isDoubleClick: ${currentTime - lastClickTime}")
        val isClick = (currentTime - lastClickTime) <= SPACE_TIME
        lastClickTime = currentTime
        return isClick
    }


    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public fun onClickListener(proceed: ProceedingJoinPoint) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "onClickListener: 单击")
        }
        if (!isDoubleClick()) {
            proceed.proceed()
        }
    }
}