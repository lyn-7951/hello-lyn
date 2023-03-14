package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.InnerMarsPhoto

class PhotoGridAdapter : ListAdapter<InnerMarsPhoto,
        PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<InnerMarsPhoto>() {

        /*1.先执行
             调用此方法来确定两个对象是否代表相同的列表项*/
        override fun areItemsTheSame(oldItem: InnerMarsPhoto, newItem: InnerMarsPhoto): Boolean {
            return oldItem.id== newItem.id
        }

        /*2.执行了1返回true，再执行2
            会在需要检查两个列表项的数据是否相同时调用此方法。MarsPhoto 中的重要数据是图片网址
        *   return false修改*/
        override fun areContentsTheSame(oldItem: InnerMarsPhoto, newItem: InnerMarsPhoto): Boolean {
            return oldItem.imagePath == newItem.imagePath
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPhotoViewHolder {
        return MarsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    class MarsPhotoViewHolder(private var binding:
                              GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(MarsPhoto: InnerMarsPhoto) {
            binding.photo =MarsPhoto
            binding.executePendingBindings()
        }
    }


}
