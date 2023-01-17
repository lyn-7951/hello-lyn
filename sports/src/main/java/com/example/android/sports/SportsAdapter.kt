package com.example.android.sports

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.sports.databinding.SportsListItemBinding
import com.example.android.sports.model.Sport

/*ListAdapter 不需要重写getItemCount()*/
class SportsAdapter(private val onItemClicked: (Sport) -> Unit, private val ListChangeListener : () -> Unit) :
    ListAdapter<Sport, SportsAdapter.SportsViewHolder>(DiffCallback) {

    /*列表更新时调用*/
    override fun onCurrentListChanged(previousList: List<Sport>, currentList: List<Sport>) {
        super.onCurrentListChanged(previousList, currentList)

        // Call your listener here
//        ListChangeListener()
    }

    private lateinit var context: Context

    class SportsViewHolder(private var binding: SportsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sport: Sport, context:Context) {
            binding.title.text = context.getString(sport.titleResourceId)
            binding.subTitle.text = context.getString(sport.subTitleResourceId)
            // Coil library.
            binding.sportsImage.load(sport.imageResourceId)
//            binding.sportsImage.setImageResource(sport.imageResourceId)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportsViewHolder {
        context = parent.context
        return SportsViewHolder(
            SportsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        )
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        Log.e("aa", position.toString())
        val current = getItem(position)//从数据列表中获取指定位置的元素
        holder.itemView.setOnClickListener {
//            current.subTitleResourceId=R.string.text
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    /*操作增删改除不用被唤醒
        * 通过 DiffUtil 计算前后集合的差异, 得出增删改的结果. 通知Adapter做出对应更新*/

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Sport>() {
            /*比较两个对象是否是同一个 Item*/
            override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return (
                        oldItem.id == newItem.id
                        )
            }

            /*在已经确定同一 Item 的情况下, 再确定是否有内容更新
            * ，如果不一致，那么它就将对列表进行重绘和动画加载，反之，表示你已经显示了这个对象的内容并且没有任何的变化，*/
            override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {

                Log.e("bb", ((Sport(oldItem.id,oldItem.subTitleResourceId,oldItem.titleResourceId,oldItem.imageResourceId,oldItem.sportsImageBanner,oldItem.newsDetails)==
                        Sport(newItem.id,newItem.subTitleResourceId,newItem.titleResourceId,newItem.imageResourceId,newItem.sportsImageBanner,newItem.newsDetails)).toString()))
                return  (
                         false
                        )//false修改
            }


        }
    }
}
