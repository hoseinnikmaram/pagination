package com.nikmaram.list.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginStart
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikmaram.list.R
import com.nikmaram.list.databinding.ItemBinding
import com.nikmaram.list.model.Photo

class RecyclerViewAdapter(val callback: (position:Int) -> Unit) : PagingDataAdapter<Photo,RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.src = item?.image ?: R.mipmap.ic_launcher
        holder.itemView.setOnClickListener {
            callback(position)
        }
        val context = holder.binding.root.context
      //  holder.binding.marginHorizontal = if (position == 0) context.dip2px((holder.itemView.width/2).toFloat()) else context.dip2px(8f)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        MyViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    class MyViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    class DiffUtilCallBack: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.image == newItem.image
        }

    }
}