package rui.todd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import rui.fragment.OneFragment
import rui.fragment.TwoFragment

open class FragmentActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "FragmentActivity"
        const val flag = "1"
        const val flag2 = "2"
    }

    var isOne = true
    lateinit var f1: OneFragment
    lateinit var f2: TwoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        val beginTransaction = supportFragmentManager.beginTransaction()
        val findFragmentByTag = supportFragmentManager.findFragmentByTag(flag)
        val findFragmentByTag2 = supportFragmentManager.findFragmentByTag(flag2)
        if (findFragmentByTag == null)
            f1 = OneFragment()
        if (findFragmentByTag2 == null)
            f2 = TwoFragment()
        beginTransaction.replace(R.id.container, f1, flag)
        beginTransaction.commitAllowingStateLoss()
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: $TAG")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: $TAG")
    }

    fun change(view: View) {
        isOne = !isOne
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.container, if (isOne) f1 else f2, flag)
        beginTransaction.commitAllowingStateLoss()
    }

}