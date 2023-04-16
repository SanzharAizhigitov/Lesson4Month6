package com.geektech.lesson4month6.ui.videos.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.lesson4month6.core.ext.loadImage
import com.geektech.lesson4month6.data.remote.model.Item
import com.geektech.lesson4month6.databinding.VideosItemBinding

class VideosAdapter(private val onClick: (id:String) -> Unit) :
    RecyclerView.Adapter<VideosAdapter.PlaylistItemViewHolder>() {
    private val data = arrayListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(newData: List<Item>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        return PlaylistItemViewHolder(
            VideosItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PlaylistItemViewHolder(private val binding: VideosItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            with(binding) {
                image.loadImage(item.snippet.thumbnails.medium.url)
                titleTv.text = item.snippet.title
                itemView.setOnClickListener { onClick(item.id) }
            }
        }
    }
}