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
import com.geektech.lesson4month6.ui.player.PLayerActivity
import com.geektech.lesson4month6.ui.playlists.PlayListActivity
import com.geektech.lesson4month6.ui.videos.adapter.VideosAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideosActivity : BaseActivity<ActivityVideosBinding, VideosViewModel>() {
    private val adapter by lazy { VideosAdapter(this::onCLick) }
    private val playlistInfo by lazy { intent.getSerializableExtra("VIDEOS_KEY") as PlaylistInfo }
    private var playlistItemData = listOf<Item>()
    private var videosId = arrayListOf<String>()
    override val viewModel: VideosViewModel by viewModel()

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
        binding.tvCounterVideo.text = "${playlistInfo.itemCount} video series "
    }

    override fun initViewModel() {
        super.initViewModel()
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
        binding.backTv.setOnClickListener { finish() }
        binding.backImg.setOnClickListener { finish() }
    }

    private fun onCLick(item: Item) {
        Intent(this@VideosActivity, PLayerActivity::class.java).apply {
            putExtra(
                "PLAYER_KEY", PlaylistInfo(
                    item.id,
                    item.snippet.title,
                    item.snippet.description,
                    item.contentDetails.itemCount
                )
            )
            startActivity(this)
        }
    }
}