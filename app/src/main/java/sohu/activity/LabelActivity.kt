package sohu.activity

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.item_label_layout.view.*
import rui.todd.databinding.ActivityLabelBinding
import rui.todd.databinding.ItemLabelLayoutBinding
import rui.utils.dp2px

class LabelActivity : AppCompatActivity() {

    val list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLabelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val manager = StaggeredGridLayoutManager(5, RecyclerView.HORIZONTAL)
        binding.rv.layoutManager = manager
        for (i in 0 until 30) {
            list.add("网易云$i")
        }
        val adapter = Adapter(this, list)
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(ItemDecoration(3.dp2px()))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: String) {
            itemView.tv.text = word
        }
    }

    class Adapter(context: Context, list: List<String>) : RecyclerView.Adapter<Holder>() {

        private val mList = list
        private val mContext = context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val itemBinding =
                ItemLabelLayoutBinding.inflate(LayoutInflater.from(mContext), parent, false)
            return Holder(itemBinding.root)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(mList[position % mList.size])
        }

        override fun getItemCount(): Int {
            return Int.MAX_VALUE
        }

    }

    class ItemDecoration(space: Int) : RecyclerView.ItemDecoration() {
        private val mSpace = space

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = mSpace
            outRect.right = mSpace
            outRect.top = mSpace
            outRect.bottom = mSpace
        }
    }

}