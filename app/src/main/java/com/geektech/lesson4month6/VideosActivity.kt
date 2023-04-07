package com.geektech.lesson4month6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.lesson4month6.base.BaseActivity
import com.geektech.lesson4month6.databinding.ActivityPlaylistsBinding
import com.geektech.lesson4month6.databinding.ActivityVideosBinding

class VideosActivity : BaseActivity<ActivityVideosBinding, MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
    }

    override val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }


    override fun inflateViewBinding(): ActivityVideosBinding {
        return ActivityVideosBinding.inflate(layoutInflater)
    }
    override fun initListener() {
        super.initListener()
        val result = intent.getStringExtra("id")
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}