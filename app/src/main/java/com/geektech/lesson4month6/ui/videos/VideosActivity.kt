package com.geektech.lesson4month6.ui.videos

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.lesson4month6.core.network.isOnline.ConnectionLiveData
import com.geektech.lesson4month6.core.network.result.Status
import com.geektech.lesson4month6.core.ui.BaseActivity
import com.geektech.lesson4month6.data.remote.model.Item
import com.geektech.lesson4month6.data.remote.model.PlaylistInfo
import com.geektech.lesson4month6.databinding.ActivityVideosBinding
import com.geektech.lesson4month6.ui.playlists.PlayListActivity
import com.geektech.lesson4month6.ui.videos.adapter.VideosAdapter

class VideosActivity : BaseActivity<ActivityVideosBinding, VideosViewModel>() {
    private val adapter by lazy { VideosAdapter() }

    private val playlistInfo by lazy { intent.getSerializableExtra("VIDEOS_KEY") as PlaylistInfo }
    private var playlistItemData = listOf<Item>()
    private var videosId = arrayListOf<String>()
    override lateinit var viewModel: VideosViewModel

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.noInternet.visibility = View.GONE
                binding.content.visibility = View.VISIBLE
            } else {
                binding.noInternet.visibility = View.VISIBLE
                binding.content.visibility = View.GONE
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.videosRv.adapter = adapter
        binding.titleTv.text = playlistInfo.title
        binding.descTv.text = playlistInfo.desc
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[VideosViewModel::class.java]
        getVideos()
    }

    private fun getVideos() {
        viewModel.getVideos(playlistInfo.id, playlistInfo.itemCount).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    playlistItemData = it.data!!.items
                    getVideosId()
                    adapter.addData(playlistItemData)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> Log.e("aga", "LOADING: ")
            }
        }
    }

    private fun getVideosId() {
        viewModel.getVideosId(playlistItemData)
        viewModel.liveVideosId.observe(this) {
            videosId.addAll(it)
        }
    }
        override fun inflateViewBinding(): ActivityVideosBinding {
            return ActivityVideosBinding.inflate(layoutInflater)
        }

        override fun initListener() {
            super.initListener()
            val intentBack = Intent(this@VideosActivity, PlayListActivity::class.java)
            binding.backTv.setOnClickListener { finish() }
            binding.backImg.setOnClickListener { finish() }
            val result = intent.getStringExtra("id")
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
    }