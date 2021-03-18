package rui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rui.todd.R

class OneFragment : Fragment() {
    companion object {
        private const val TAG = "OneFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_one, container, false)
//        val beginTransaction = childFragmentManager.beginTransaction()
//        beginTransaction.replace(R.id.container, OneChildFragment())
//        beginTransaction.commitAllowingStateLoss()
        return v
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: $TAG")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: $TAG")
    }

}