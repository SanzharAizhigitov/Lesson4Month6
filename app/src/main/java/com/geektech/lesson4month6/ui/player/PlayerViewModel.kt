package com.geektech.lesson4month6.ui.player

import androidx.lifecycle.LiveData
import com.geektech.lesson4month6.core.network.result.Resource
import com.geektech.lesson4month6.core.ui.BaseViewModel
import com.geektech.lesson4month6.data.remote.model.Video
import com.geektech.lesson4month6.repository.Repository

class PlayerViewModel(private val repository: Repository):BaseViewModel() {
    fun getVideo(id:String): LiveData<Resource<Video>> {
        return repository.getVideo(id)
    }
}