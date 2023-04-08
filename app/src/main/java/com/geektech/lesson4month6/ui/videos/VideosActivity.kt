package com.geektech.lesson4month6.ui.videos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.lesson4month6.R
import com.geektech.lesson4month6.core.network.isOnline.ConnectionLiveData
import com.geektech.lesson4month6.core.ui.BaseActivity
import com.geektech.lesson4month6.databinding.ActivityVideosBinding
import com.geektech.lesson4month6.ui.playlists.PlaylistsViewModel

class VideosActivity : BaseActivity<ActivityVideosBinding, PlaylistsViewModel>(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
    }

    override val viewModel: PlaylistsViewModel by lazy { ViewModelProvider(this)[PlaylistsViewModel::class.java] }

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.content.visibility = View.VISIBLE
                binding.noInet.visibility = View.GONE
            } else {
                binding.content.visibility = View.GONE
                binding.noInet.visibility = View.VISIBLE
            }
        }
    }
    override fun inflateViewBinding(): ActivityVideosBinding {
        return ActivityVideosBinding.inflate(layoutInflater)
    }
    override fun initListener() {
        super.initListener()
        val result = intent.getStringExtra("id")
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}