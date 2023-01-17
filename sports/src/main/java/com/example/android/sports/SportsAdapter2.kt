package com.example.android.sports

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.sports.databinding.SportsListItemBinding
import com.example.android.sports.model.Sport

/*ListAdapter 不需要重写getItemCount()*/
class SportsAdapter2(
    private val onItemClicked: (Sport) -> Unit,
    private val context: Context,
    private val dataset:List<Sport>) :
    RecyclerView.Adapter<SportsAdapter2.SportsViewHolder>() {

    class SportsViewHolder(private var binding: SportsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView =binding.title
        val subTitle: TextView =binding.subTitle
        val newsTitle: TextView =binding.newsTitle
        val sportsImage: ImageView =binding.sportsImage
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportsViewHolder {
        return SportsViewHolder(
            SportsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val item =dataset[position]//从数据列表中获取指定位置的元素

        holder.title.text = context.getString(item.titleResourceId)
        holder.subTitle.text = context.getString(item.subTitleResourceId)
        holder.sportsImage.load(item.imageResourceId)

        holder.itemView.setOnClickListener {
            onItemClicked(item)
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return dataset.size
    }
}
