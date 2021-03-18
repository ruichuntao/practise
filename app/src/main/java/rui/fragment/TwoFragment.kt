package rui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rui.todd.R

class TwoFragment : Fragment() {
    companion object {
        private const val TAG = "TwoFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
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