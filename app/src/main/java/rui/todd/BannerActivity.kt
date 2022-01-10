package rui.todd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import rui.todd.databinding.ActivityBanner2Binding
import rui.todd.databinding.ActivityBannerBinding
import rui.view.PieView

class BannerActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: ActivityBanner2Binding
    private lateinit var adapter: Adapter
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBanner2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = ArrayList<Bean>()
        for (i: Int in 1..10) {
            list.add(Bean(i))
        }
        adapter = Adapter(this, list)
        binding.recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.recyclerView.layoutManager = layoutManager
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.recyclerView)
        mHandler.postDelayed(runnable, 1000)
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                flag = newState == RecyclerView.SCROLL_STATE_IDLE
            }
        })
//        binding.recyclerView.addView(PieView(this))
    }


    private val mHandler = Handler {
        false
    }

    val runnable = object : Runnable {
        override fun run() {
            if (flag)
                binding.recyclerView.smoothScrollToPosition((layoutManager.findFirstVisibleItemPosition() + 1) % 10)
            mHandler.postDelayed(this, 1000)
        }
    }


    class Bean(i: Int) {
        var id: Int = i
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var iv: ImageView = itemView.findViewById(R.id.iv)

        fun bind(bean: Bean) {
            if (bean.id % 2 == 0) {
                iv.setImageResource(R.drawable.keji)
            } else {
                iv.setImageResource(R.drawable.background)
            }
        }
    }

    class Adapter(context: Context, list: List<Bean>) : RecyclerView.Adapter<Holder>() {

        private val mContext = context
        private val mList = list

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(
                LayoutInflater.from(mContext).inflate(R.layout.item_banner_layout, parent, false)
            )
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(mList[position])
        }

        override fun getItemCount(): Int {
            return mList.size
        }

    }

}