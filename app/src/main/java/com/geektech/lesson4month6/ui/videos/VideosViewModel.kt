package com.geektech.lesson4month6.ui.videos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.lesson4month6.App
import com.geektech.lesson4month6.core.network.result.Resource
import com.geektech.lesson4month6.core.ui.BaseViewModel
import com.geektech.lesson4month6.data.remote.model.Item
import com.geektech.lesson4month6.data.remote.model.PlaylistItem
import com.geektech.lesson4month6.repository.Repository

class VideosViewModel(private val repository: Repository) : BaseViewModel() {
    private val mutableVideosId: MutableLiveData<List<String>> = MutableLiveData()
    val liveVideosId: LiveData<List<String>> = mutableVideosId

    fun getVideos(playlistId: String, itemCount: Int): LiveData<Resource<PlaylistItem>> {
        return repository.getVideos(playlistId, itemCount)
    }

    fun getVideosId(data: List<Item>) {
        val result = arrayListOf<String>()
        for (i in data) {
            result.add(i.contentDetails.videoId)
        }
        mutableVideosId.value = (result)
    }
}