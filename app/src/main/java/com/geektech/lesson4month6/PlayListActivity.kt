package com.geektech.lesson4month6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.geektech.lesson4month6.model.Item
import androidx.lifecycle.ViewModelProvider
import com.geektech.lesson4month6.base.BaseActivity
import com.geektech.lesson4month6.databinding.ActivityPlaylistsBinding
import com.geektech.lesson4month6.playlistrv.adapter.PlaylistAdapter

class PlayListActivity : BaseActivity<ActivityPlaylistsBinding, MainViewModel>() {
    private lateinit var adapter: PlaylistAdapter
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initRV() {
        super.initRV()
        adapter = PlaylistAdapter(this::onClick)
    }
    override fun initViewModel() {
        super.initViewModel()

        viewModel.playLists().observe(this) {
                binding.rvPlaylist.adapter = adapter
                adapter.setList(it.items)
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }
     fun onClick( item: Item){
         val intent = Intent(this@PlayListActivity, VideosActivity::class.java)
         intent.putExtra("id", item.snippet.title)
         Toast.makeText(this, item.snippet.title, Toast.LENGTH_SHORT).show()
         startActivity(intent)
    }
}