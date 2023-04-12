package com.geektech.lesson4month6.ui.playlists

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.geektech.lesson4month6.data.remote.model.Item
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.lesson4month6.App
import com.geektech.lesson4month6.core.network.isOnline.ConnectionLiveData
import com.geektech.lesson4month6.core.network.result.Status
import com.geektech.lesson4month6.ui.videos.VideosActivity
import com.geektech.lesson4month6.core.ui.BaseActivity
import com.geektech.lesson4month6.data.remote.model.PlaylistInfo
import com.geektech.lesson4month6.databinding.ActivityPlaylistsBinding
import com.geektech.lesson4month6.ui.playlists.adapter.PlaylistAdapter

class PlayListActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>(){
    private val adapter: PlaylistAdapter by lazy { PlaylistAdapter(this::onClick) }
    private var playlistData = listOf<Item>()
    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun checkConnection() {
        super.checkConnection()
        var connection = ConnectionLiveData(application)
        connection.observe(this) { isConnected ->
            if (isConnected) {
                binding.rvPlaylist.visibility = View.VISIBLE
                binding.noInet.visibility = View.GONE
            } else {
                binding.rvPlaylist.visibility = View.GONE
                binding.noInet.visibility = View.VISIBLE
            }
        }
    }

    override fun initRV() {
        super.initRV()
        binding.rvPlaylist.layoutManager = LinearLayoutManager(this)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        viewModel.getPlayLists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.rvPlaylist.adapter = adapter
                    it.data?.let { it1 -> adapter?.setList(it1.items) }
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }
            }
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    private fun onClick(position: Int): Unit {
        val intent = Intent(this, VideosActivity::class.java)
        intent.putExtra(
            "VIDEOS_KEY",
            PlaylistInfo(
                playlistData[position].id,
                playlistData[position].snippet.title,
                playlistData[position].snippet.description,
                playlistData[position].contentDetails.itemCount
            )
        )
        startActivity(intent)
    }
}