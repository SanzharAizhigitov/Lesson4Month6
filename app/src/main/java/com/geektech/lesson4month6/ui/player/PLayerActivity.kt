package com.geektech.lesson4month6.ui.player

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.geektech.lesson4month6.core.network.isOnline.ConnectionLiveData
import com.geektech.lesson4month6.core.network.result.Status
import com.geektech.lesson4month6.core.ui.BaseActivity
import com.geektech.lesson4month6.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class PLayerActivity  : BaseActivity<ActivityPlayerBinding, PlayerViewModel>() {
companion object{
   val VIDEO_KEY = "VIDEO_KEY"
}
    override val viewModel: PlayerViewModel by viewModel()
    private lateinit var player: ExoPlayer

    override fun inflateViewBinding(): ActivityPlayerBinding = ActivityPlayerBinding.inflate(layoutInflater)

    @Suppress("UNREACHABLE_CODE")
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        player = ExoPlayer.Builder(context).build()
        binding.player.player = player
        player.setMediaItem(MediaItem.fromUri("https://youtu.be/dQw4w9WgXcQ"))
        player.prepare()

    }
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
    override fun initViewModel() {
        super.initViewModel()
        getVideo()
    }
    override fun initView() {
        super.initView()
    }
    override fun initListener() {
        super.initListener()
        binding.backTv.setOnClickListener { finish() }
        binding.backImg.setOnClickListener { finish() }
        binding.player.setOnClickListener{
            player.play()
        }
    }
    private fun getVideo() {
        viewModel.getVideo(intent.getStringExtra(VIDEO_KEY)!!).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.titleTv.text =it.data!!.items[0].snippet.title
                    binding.descTv.text =it.data!!.items[0].snippet.description
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> Log.e("aga", "LOADING: ")
            }
        }
    }
}