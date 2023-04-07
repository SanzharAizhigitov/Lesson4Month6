package com.geektech.lesson4month6.playlistrv.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.lesson4month6.MainViewModel
import com.geektech.lesson4month6.databinding.PlaylistItemBinding
import com.geektech.lesson4month6.ext.loadImage
import com.geektech.lesson4month6.model.Item
import com.geektech.lesson4month6.model.Playlists

class PlaylistAdapter(private val onClick: (Item) -> Unit) : Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setList(liste: List<Item>) {
        this.list = liste as ArrayList<Item>
        notifyDataSetChanged()
    }

    private var list = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlaylistViewHolder(private val binding: PlaylistItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.videoIv.loadImage(item.snippet.thumbnails.default.url)
            binding.titleTv.text = item.snippet.title
            binding.seriesTv.text = "${item.contentDetails.itemCount} + video series"
            binding.item.setOnClickListener {
onClick.invoke(item)
            }
        }


    }

}