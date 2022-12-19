package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

class ItemAdapter(
    private val context: Context,
    private val dataset:List<Affirmation>
    ):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /* 创造列表项视图
        parent 参数，即新列表项视图将作为子视图附加到的视图组。父项是 RecyclerView。
        viewType 参数，当同一个 RecyclerView 中存在多种列表项视图类型时，此参数可发挥重要作用。
        如果在 RecyclerView 内显示不同的列表项布局，就会有不同的列表项视图类型。您只能循环使用列表项视图类型相同的视图。
        针对您的情况而言，只有一个列表项布局和一个列表项视图类型，因此您不必担心此参数。*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    /*替换列表项的内容
    *   position:列表项的位置  */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item=dataset[position]//列表项
        holder.textView.text=context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

//    返回数据集的大小
    override fun getItemCount(): Int {
        return dataset.size
    }

    //    嵌套类
    class ItemViewHolder(private val view: View):RecyclerView.ViewHolder(view){
        val textView:TextView=view.findViewById(R.id.item_title)
        val imageView:ImageView=view.findViewById(R.id.item_image)
    }
}